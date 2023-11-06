package br.com.api.apiengerb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.api.apiengerb.modelo.EmpresaModelo;
import br.com.api.apiengerb.repositorio.EmpresaRepositorio;
import br.com.api.apiengerb.repositorio.UserRepositorio;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepositorio er;

    @Autowired
    private UserRepositorio ur;

    public ResponseEntity<?> cadastraremp(EmpresaModelo em) {
        return new ResponseEntity<EmpresaModelo>(er.save(em), HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<?> alterar(EmpresaModelo em, Integer idUser) {
        EmpresaModelo empresa = this.carregarEmpresa(idUser);
        if (empresa != null) {
            empresa.setLogin(em.getLogin());
            empresa.setNomeUser(em.getNomeUser());
            empresa.setPortaEmpresa(em.getPortaEmpresa());
            empresa.setSmtpEmpresa(em.getSmtpEmpresa());
        }
        return new ResponseEntity<EmpresaModelo>(er.save(empresa), HttpStatus.OK);
    }

    public EmpresaModelo carregarEmpresa(Integer idUser) {
        return ur.findById(idUser)
                .filter(usuario -> usuario instanceof EmpresaModelo)
                .map(usuario -> (EmpresaModelo) usuario)
                .orElse(null);
    }
}
