package br.com.api.apiengerb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.apiengerb.modelo.DocumentoModelo;
import br.com.api.apiengerb.modelo.RespostaModelo;
import br.com.api.apiengerb.modelo.UserModelo;
import br.com.api.apiengerb.repositorio.DocumentoRepositorio;
import br.com.api.apiengerb.repositorio.UserRepositorio;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DocuementoService {

    @Autowired
    private DocumentoRepositorio dr;

    @Autowired
    private UserRepositorio ur;
    
    @Autowired
    private RespostaModelo rm;

    public DocumentoModelo cadastrarDocumento(DocumentoModelo documento, Integer userId) {
        UserModelo userOptional = ur.findByIdUser(userId);

        if (userOptional != null) {
            UserModelo user = userOptional;

            // Associe o usuário ao documento
            documento.setUsuario(user);

            // Salve o documento no banco de dados
            return dr.save(documento);
        } else {
            // Trate o caso em que o usuário com o ID fornecido não foi encontrado
            throw new EntityNotFoundException("Usuário com o ID " + userId + " não encontrado.");
        }

    }

     public List<DocumentoModelo> listar() {
        return dr.findAll();
    }

    public List<DocumentoModelo> listarDocumentosPorUsuario(Integer idUser) {
        return dr.findByUsuario_IdUser(idUser);
    }

     public ResponseEntity<RespostaModelo> remover(int idDocumento) {

        dr.deleteById(idDocumento);

        rm.setMessage("Documento removido com Sucesso!");
        return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);

    }
}
