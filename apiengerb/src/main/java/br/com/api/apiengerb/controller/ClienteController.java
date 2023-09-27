package br.com.api.apiengerb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.apiengerb.modelo.ClienteModelo;
import br.com.api.apiengerb.services.ClienteService;

@RestController
public class ClienteController {
    
    @Autowired
    private ClienteService cs;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody ClienteModelo cm){
        return cs.cadastrarAlterar(cm, "cadastrar");

    }
     @PutMapping("/alterar")
    public ResponseEntity<?> alterar(@RequestBody ClienteModelo cm){
        return cs.cadastrarAlterar(cm, "alterar");

    }

    @GetMapping("/listar")
    public Iterable<ClienteModelo> listar(){
        return cs.listar();
    }

    @GetMapping("/")
    public String rota(){
        return "API Funcionando";
    }
}
