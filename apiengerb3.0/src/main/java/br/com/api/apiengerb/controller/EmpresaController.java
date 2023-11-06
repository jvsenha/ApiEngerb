package br.com.api.apiengerb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.apiengerb.modelo.EmpresaModelo;
import br.com.api.apiengerb.modelo.RespostaModelo;
import br.com.api.apiengerb.services.EmpresaService;

@RestController
@RequestMapping("/empresa")
@CrossOrigin(origins = "http://localhost:3000")
public class EmpresaController {

    @Autowired
    private EmpresaService es;

    @Autowired
    private RespostaModelo rm;

    @PutMapping("/alterar/{idUser}")
    public ResponseEntity<?> alterar(@RequestBody EmpresaModelo em, @PathVariable Integer idUser) {

        es.alterar(em, idUser);
        rm.setMessage("Alterado com Sucesso!");
        return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);

    }

    // Rota para listar empresa
    @GetMapping("/carregar/{idUser}")
    public ResponseEntity<EmpresaModelo> carregar(@PathVariable Integer idUser) {
        EmpresaModelo empresa = es.carregarEmpresa(idUser);

        if (empresa != null) {
            return ResponseEntity.ok(empresa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}