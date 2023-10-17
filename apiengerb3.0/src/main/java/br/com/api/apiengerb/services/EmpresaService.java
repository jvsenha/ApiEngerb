package br.com.api.apiengerb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.apiengerb.modelo.EmpresaModelo;
import br.com.api.apiengerb.repositorio.EmpresaRepositorio;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepositorio er;

    public ResponseEntity<?> cadastraremp(EmpresaModelo em) {
        return new ResponseEntity<EmpresaModelo>(er.save(em), HttpStatus.CREATED);
    }
}
