package br.com.api.apiengerb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.apiengerb.modelo.ClienteModelo;
import br.com.api.apiengerb.modelo.RespostaModelo;
import br.com.api.apiengerb.services.ClienteService;

@RestController
@CrossOrigin(origins="http://localhost:8080")
public class ClienteController {
    
    @Autowired
    private ClienteService cs;

    @DeleteMapping("/remover/{idCliente}")
    public ResponseEntity<RespostaModelo> remover(@PathVariable Integer idCliente){
        return cs.remover(idCliente);
    }

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
