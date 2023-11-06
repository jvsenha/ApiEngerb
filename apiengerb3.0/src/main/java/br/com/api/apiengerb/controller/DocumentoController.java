package br.com.api.apiengerb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.apiengerb.modelo.DocumentoModelo;
import br.com.api.apiengerb.modelo.RespostaModelo;
import br.com.api.apiengerb.services.DocuementoService;


@RestController
@RequestMapping("/documentos")
public class DocumentoController {

   

    @Autowired
   private DocuementoService ds;

   @Autowired
   private RespostaModelo rm;

    @PostMapping("/cadastrar")
    public ResponseEntity<DocumentoModelo> cadastrarDocumento(@RequestBody DocumentoModelo documento, @RequestParam("userId") Integer userId) {
        DocumentoModelo novoDocumento = ds.cadastrarDocumento(documento, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDocumento);
    }

      @GetMapping("/listar")
    public ResponseEntity<List<DocumentoModelo>> listarClientes() {
        List<DocumentoModelo> documento = ds.listar();
        return ResponseEntity.ok(documento);
    }
     @GetMapping("/listar/{idUser}")
    public ResponseEntity<List<DocumentoModelo>> listarDocumentosPorUsuario(@PathVariable Integer idUser) {
        List<DocumentoModelo> documentos = ds.listarDocumentosPorUsuario(idUser);
        return ResponseEntity.ok(documentos);
    }

     @DeleteMapping("/remover/{idDocumento}")
    public ResponseEntity<RespostaModelo> remover(@PathVariable String idDocumento) {
        rm.setMessage("removido com Sucesso!");
        return ds.remover(idDocumento);
    }
}