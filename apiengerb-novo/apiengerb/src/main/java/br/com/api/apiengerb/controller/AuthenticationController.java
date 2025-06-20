package br.com.api.apiengerb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.apiengerb.modelo.AuthenticationDTO;
import br.com.api.apiengerb.modelo.CadastroDTO;
import br.com.api.apiengerb.modelo.ClienteModelo;
import br.com.api.apiengerb.modelo.RespostaModelo;
import br.com.api.apiengerb.repositorio.ClienteRepositorio;
import br.com.api.apiengerb.repositorio.UserRepositorio;
import br.com.api.apiengerb.services.UserService;
import jakarta.validation.Valid;

@RestController // Declarando classe como RestController
@RequestMapping("auth") // mapeando URL com esse inicio
@CrossOrigin(origins = "http://localhost:3000") // permitindo CORS
public class AuthenticationController {

    // Injeção de dependencia
    //@Autowired
    //private AuthenticationManager authenticationManager;

    @Autowired
    private UserService us;

    @Autowired
    private UserRepositorio ur;
    
    @Autowired
    private ClienteRepositorio cr;

    @Autowired
    private RespostaModelo rm;

    // Rota para login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {
        if (this.ur.findByLogin(data.login()) == null) {
            rm.setMessage("login ou senha não existe!");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else {
            var UsernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senhaUser());
           // var auth = this.authenticationManager.authenticate(UsernamePassword);

            return ResponseEntity.ok().body(UsernamePassword);
        }
    }

    // Rota para cadastro
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarCliente(@RequestBody @Valid CadastroDTO data, ClienteModelo cm) {
        if (this.ur.findByLogin(data.login()) != null) {
            rm.setMessage("Esse usuario já existe!");
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
}
