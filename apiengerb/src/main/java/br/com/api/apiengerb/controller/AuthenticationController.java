package br.com.api.apiengerb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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
import br.com.api.apiengerb.modelo.UserModelo;
import br.com.api.apiengerb.repositorio.UserRepositorio;
import br.com.api.apiengerb.security.CustomPasswordEncoder;
import br.com.api.apiengerb.services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins="http://localhost:3000")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService us;
 
    @Autowired
    private UserRepositorio ur;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var UsernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senhaUser());
        var auth = this.authenticationManager.authenticate(UsernamePassword);

        return ResponseEntity.ok().build();
    }
    
      @PostMapping("/cadastrar")
     public ResponseEntity<?> cadastrarCliente(@RequestBody @Valid CadastroDTO data, ClienteModelo cm){
        if(this.ur.findByLogin(data.login())!= null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new CustomPasswordEncoder().encode(data.senhaUser());
        ClienteModelo newUser = new ClienteModelo(data.login(), encryptedPassword, data.role(), data.cliente(), data.nomeUser(), data.emailCliente(),  data.pastaCliente());
        this.us.cadastrarAlterar(newUser, "cadastrar");
        return ResponseEntity.ok().body(null);
    }

}

