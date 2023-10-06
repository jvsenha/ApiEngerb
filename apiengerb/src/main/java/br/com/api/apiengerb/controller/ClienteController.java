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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.apiengerb.modelo.ClienteModelo;
import br.com.api.apiengerb.modelo.RespostaModelo;
import br.com.api.apiengerb.services.ClienteService;
import br.com.api.apiengerb.services.UserService;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins="http://localhost:3000")
public class ClienteController {
    
    @Autowired
    private ClienteService cs;

    @Autowired
     private UserService us;

    @DeleteMapping("/remover/{idCliente}")
    public ResponseEntity<RespostaModelo> remover(@PathVariable Integer idCliente){
        return cs.remover(idCliente);
    }

    @PostMapping("/cadastrar")
     public ResponseEntity<?> cadastrarCliente(@RequestBody  ClienteModelo cm){
        return us.cadastrarAlterar(cm, "cadastrar");

    }

     @PutMapping("/alterarc")
    public ResponseEntity<?> alterar(@RequestBody ClienteModelo cm){
        return cs.cadastrarAlterarCli(cm, "alterar");

    }

    @GetMapping("/listarC")
    public Iterable<ClienteModelo> listar(){
        return cs.listar();
    }

    @GetMapping("/7")
    public String rota(){
        return "API Funcionando";
    }
}
