package br.com.api.apiengerb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.apiengerb.modelo.PasswordResetInput;
import br.com.api.apiengerb.repositorio.ClienteRepositorio;
import br.com.api.apiengerb.services.UserPasswordService;
import jakarta.validation.Valid;


@RestController // Declarando classe como RestController
@RequestMapping("reset") // mapeando URL com esse inicio 
public class PasswordResetController {
    @Autowired
    private  UserPasswordService userPasswordService;
   

    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestBody @Valid PasswordResetInput input) {
       
    }

}
