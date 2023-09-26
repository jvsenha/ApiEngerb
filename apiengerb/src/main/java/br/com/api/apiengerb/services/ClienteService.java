package br.com.api.apiengerb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.apiengerb.modelo.ClienteModelo;
import br.com.api.apiengerb.repositório.ClienteRepositorio;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepositorio cr;

    
    //Método para listar todos os clientes
    public Iterable <ClienteModelo> listar(){
        return cr.findAll();
    }
    
}
