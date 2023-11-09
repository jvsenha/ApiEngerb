package br.com.api.apiengerb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.apiengerb.modelo.PasswordResetInput;
import br.com.api.apiengerb.services.ClienteService;
import jakarta.validation.Valid;

@RestController // Declarando classe como RestController
@RequestMapping("reset") // mapeando URL com esse inicio
public class PasswordResetController {

    @Autowired
    private ClienteService cs;

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody @Valid PasswordResetInput input) {
        return cs.solicitacaoReset(input.login());

    }
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetpassword(@RequestBody @Valid PasswordResetInput input) {
        return cs.resetPassword(input.login());

    }

}
