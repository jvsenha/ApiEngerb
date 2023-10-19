package br.com.api.apiengerb.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.api.apiengerb.modelo.AuthenticationDTO;
import br.com.api.apiengerb.modelo.CadastroDTO;
import br.com.api.apiengerb.modelo.CadastroEmpDTO;
import br.com.api.apiengerb.modelo.ClienteModelo;
import br.com.api.apiengerb.modelo.EmpresaModelo;
import br.com.api.apiengerb.modelo.LoginResponseDTO;
import br.com.api.apiengerb.modelo.RespostaModelo;
import br.com.api.apiengerb.modelo.TokenDTO;
import br.com.api.apiengerb.repositorio.ClienteRepositorio;
import br.com.api.apiengerb.repositorio.EmpresaRepositorio;
import br.com.api.apiengerb.repositorio.UserRepositorio;
import br.com.api.apiengerb.security.TokenService;
import br.com.api.apiengerb.services.UserService;
import jakarta.validation.Valid;

@RestController // Declarando classe como RestController
@RequestMapping("auth") // mapeando URL com esse inicio // permitindo CORS
public class AuthenticationController{

    
    // Injeção de dependencia
    @Autowired
    private ClienteRepositorio cr;

    @Autowired
    private EmpresaRepositorio er;

    @Autowired
    private RespostaModelo rm;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService us;

    @Autowired
    private UserRepositorio ur;

    @Autowired
    private TokenService ts;

    
    
    // Rota para login
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {
        var UsernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senhaUser());
        var auth = this.authenticationManager.authenticate(UsernamePassword);
        var token = ts.gerarToken((ClienteModelo) auth.getPrincipal());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

     @PostMapping("/loginemp")
    public ResponseEntity<?> loginemp(@RequestBody @Valid AuthenticationDTO data) {
        var UsernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senhaUser());
        var auth = this.authenticationManager.authenticate(UsernamePassword);
        var token = ts.gerarTokenEmp((EmpresaModelo) auth.getPrincipal());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    // Rota para cadastro
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarCliente(@RequestBody @Valid CadastroDTO data, ClienteModelo cm) {
        if (this.ur.findByLogin(data.login()) != null) {
            rm.setMessage("Esse usuário já existe!");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        }else if (this.cr.findByEmailCliente(data.emailCliente()) != null) {
            rm.setMessage("Esse email já está cadastrado!");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else if (this.cr.findByPastaCliente(data.pastaCliente()) != null) {
            rm.setMessage("Essa pasta já existe!");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else {
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.senhaUser());
            ClienteModelo newUser = new ClienteModelo(data.login(), encryptedPassword, data.role(), data.cliente(),
                    data.nomeUser(), data.emailCliente(), data.pastaCliente());
            this.us.cadastrar(newUser);

            return ResponseEntity.ok().body(newUser);
        }
    }

    // Rota para cadastro
    @PostMapping("/cadastraremp")
    public ResponseEntity<?> cadastrarEmpresa(@RequestBody @Valid CadastroEmpDTO data, EmpresaModelo em) {
        if (this.ur.findByLogin(data.login()) != null) {
            rm.setMessage("Esse usuário já existe!");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        }else if (this.er.findBySmtpEmpresa(data.smtpEmpresa()) != null) {
            rm.setMessage("Esse email já está cadastrado!");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else if (this.er.findByPortaEmpresa(data.portaEmpresa()) != null) {
            rm.setMessage("Essa pasta já existe!");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else {
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.senhaUser());
            EmpresaModelo newUser = new EmpresaModelo(data.login(), encryptedPassword, data.role(), data.empresa(),
                    data.nomeUser(), data.smtpEmpresa(), data.portaEmpresa());
            this.us.cadastraremp(newUser);

            return ResponseEntity.ok().body(newUser);
        }
    }

    @PostMapping("/validarToken")
    public ResponseEntity<?> validarToken(@RequestBody TokenDTO data) {
        try {
            String token = data.getToken();
             UserDetails user = null;
             Collection<?> authorities=null;
            if (token != null) {
                var login = ts.validateToken(token);
                user = ur.findByLogin(login);
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authorities = user.getAuthorities();
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
           
            return ResponseEntity.ok(authorities);
        } catch (JWTVerificationException exception) {
            return ResponseEntity.badRequest().body("Token inválido");
        }
    }
}