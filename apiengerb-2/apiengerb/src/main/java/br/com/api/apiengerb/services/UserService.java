package br.com.api.apiengerb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.apiengerb.modelo.RespostaModelo;
import br.com.api.apiengerb.modelo.UserModelo;
import br.com.api.apiengerb.repositorio.UserRepositorio;

@Service
public class UserService {
    

    @Autowired
    private UserRepositorio ur;

    //
    @Autowired
    private RespostaModelo rm;

    // Método para listar todos os Usuários
    public Iterable<UserModelo> listar() {
        return ur.findAll();
    }

    // Método para cadastrar ou alterar Usuários
    public ResponseEntity<?> cadastrar(UserModelo um) {
            return new ResponseEntity<UserModelo>(ur.save(um), HttpStatus.CREATED);

    }
    
    public ResponseEntity<?> Alterar(UserModelo um) {

        if (um.getNomeUser().equals("")) {
            rm.setMessage("O nome do cliente é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else if (um.getLogin().equals("")) {
            rm.setMessage("O nome de usuario do cliente é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else if (um.getSenhaUser().equals("")) {
            rm.setMessage("a senha do cliente é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else {
                return new ResponseEntity<UserModelo>(ur.save(um), HttpStatus.OK);
        }

    }

    // Método para remove Usuários
    public ResponseEntity<RespostaModelo> remover(int idUser) {

        ur.deleteById(idUser);

        rm.setMessage("User removido com Sucesso!");
        return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);

    }
   
}
