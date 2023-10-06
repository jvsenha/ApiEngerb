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

import br.com.api.apiengerb.modelo.RespostaModelo;
import br.com.api.apiengerb.modelo.UserModelo;
import br.com.api.apiengerb.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="http://localhost:3000")
public class UserController {
 @Autowired
    private UserService us;

    @DeleteMapping("/remover/{idUser}")
    public ResponseEntity<RespostaModelo> remover(@PathVariable Integer idUser){
        return us.remover(idUser);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody UserModelo um){
        return us.cadastrarAlterar(um, "cadastrar");

    }
     @PutMapping("/alterar")
    public ResponseEntity<?> alterar(@RequestBody UserModelo um){
        return us.cadastrarAlterar(um, "alterar");

    }

    @GetMapping("/listar")
    public Iterable<UserModelo> listar(){
        return us.listar();
    }

    @GetMapping("/")
    public String rota(){
        return "API Funcionando";
    }
    
    
}
