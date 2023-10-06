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

    // Método para listar todos os clientes
    public Iterable<UserModelo> listar() {
        return ur.findAll();
    }

    // Metodo para cadastrar ou alterar Clientes
    public ResponseEntity<?> cadastrarAlterar(UserModelo um, String acao) {

        if (um.getNomeUser().equals("")) {
            rm.setMensagem("O nome do cliente é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else if (um.getLogin().equals("")) {
            rm.setMensagem("O nome de usuario do cliente é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else if (um.getSenhaUser().equals("")) {
            rm.setMensagem("a senha do cliente é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else {
            if (acao.equals("cadastrar")) {
                return new ResponseEntity<UserModelo>(ur.save(um), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<UserModelo>(ur.save(um), HttpStatus.OK);
            }
        }

    }

    // Metodo para remove Cliente
    public ResponseEntity<RespostaModelo> remover(int idUser) {

        ur.deleteById(idUser);

        rm.setMensagem("User removido com Sucesso!");
        return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);

    }
   
}
