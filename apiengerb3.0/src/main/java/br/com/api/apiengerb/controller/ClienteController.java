package br.com.api.apiengerb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import br.com.api.apiengerb.modelo.UserModelo;
import br.com.api.apiengerb.services.ClienteService;
import br.com.api.apiengerb.services.UserService;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://localhost:3000")
public class ClienteController {

    @Autowired
    private ClienteService cs;
    


    @Autowired
    private UserService us;

    

    @Autowired
    private RespostaModelo rm;


    // Rota para Remover cliente
    @DeleteMapping("/remover/{idCliente}")
    public ResponseEntity<RespostaModelo> remover(@PathVariable Integer idCliente) {
        rm.setMessage("Alterado com Sucesso!");
        return cs.remover(idCliente);
    }

    // Rota para cadastro cliente
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarCliente(@RequestBody ClienteModelo cm) {
        return us.cadastrar(cm);
    }

    // Rota para alterar cliente
    @PutMapping("/alterar/{idUser}")
    public ResponseEntity<?> alterar(@RequestBody ClienteModelo cm, @PathVariable Integer  idUser) {
       
            cs.alterar(cm, idUser);
            rm.setMessage("Alterado com Sucesso!");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);
       
    }

    // Rota para listar cliente
    @GetMapping("/carregar/{idUser}")
    public ResponseEntity<ClienteModelo> carregar(@PathVariable Integer idUser) {
        ClienteModelo cliente = cs.carregarCLiente(idUser);

        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Rota Para Alet
    @GetMapping("/listar")
    public ResponseEntity<List<ClienteModelo>> listarClientes() {
        List<ClienteModelo> clientes = cs.listar();
        return ResponseEntity.ok(clientes);
    }

    
   
    @PutMapping("/updateUser/{idUser}")
    public ResponseEntity<?> updateUser(@PathVariable int idUser, @RequestBody UserModelo updatedUser) {
        return us.updateUser(idUser, updatedUser);
    }

    @GetMapping("/isenabled/{idUser}")
    public ResponseEntity<?> isEnabled(@PathVariable Integer idUser, ClienteModelo cm) {
        return  us.statususer(idUser);
    }


    @GetMapping("/")
    public String rota() {
        return "API Funcionando";
    }
}
