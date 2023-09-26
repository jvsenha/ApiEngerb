package br.com.api.apiengerb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.apiengerb.modelo.ClienteModelo;
import br.com.api.apiengerb.services.ClienteService;

@RestController
public class ClienteController {
    
    @Autowired
    private ClienteService cs;

      @GetMapping("/listar")
    public Iterable<ClienteModelo> listar(){
        return cs.listar();
    }

    @GetMapping("/")
    public String rota(){
        return "API Funcionando";
    }
}
