package br.com.api.apiengerb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.api.apiengerb.modelo.ClienteModelo;
import br.com.api.apiengerb.modelo.PasswordResetInput;
import br.com.api.apiengerb.modelo.PasswordUpdateWithTokenInput;
import br.com.api.apiengerb.repositorio.ClienteRepositorio;
import br.com.api.apiengerb.services.UserPasswordService;
import jakarta.validation.Valid;


@RestController // Declarando classe como RestController
@RequestMapping("reset") // mapeando URL com esse inicio 
public class PasswordResetController {
    @Autowired
    private  UserPasswordService userPasswordService;
    

  
    @Autowired
    private  ClienteRepositorio cr;

   

    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestBody @Valid PasswordResetInput input) {
        ClienteModelo optionalUser = this.cr.findByEmailCliente(input.emailCliente());
        
        if (optionalUser != null) {
            String token = userPasswordService.generateTokenCliente(optionalUser);
            System.out.println(token);
        }
    }

    @PostMapping("/change-password")
    public void changePassword(@RequestBody @Valid PasswordUpdateWithTokenInput input) {
        try {
            userPasswordService.changePassword(input.password(), input.token());
        } catch (Exception e) {
            String message = "Erro ao alterar a senha usando token: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message, e);
        }
    }

}
