package br.com.api.apiengerb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.apiengerb.modelo.EmpresaModelo;
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
    public List<UserModelo> listar() {
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

    // Método para cadastrar Empresa
    public ResponseEntity<?> cadastraremp(EmpresaModelo em) {
            return new ResponseEntity<EmpresaModelo>(ur.save(em), HttpStatus.CREATED);

    }

    // Método para remove Usuários
    public ResponseEntity<RespostaModelo> remover(int idUser) {

        ur.deleteById(idUser);

        rm.setMessage("User removido com Sucesso!");
        return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);

    }

       public  ResponseEntity<?> updateUser(int userId, UserModelo updatedUser) {
        UserModelo user = ur.findByIdUser(userId);
                if (user != null) {
                    user.setEnabled(updatedUser.isEnabled());
                  return new ResponseEntity<UserModelo>(ur.save(user), HttpStatus.OK);
                } else {
                  rm.setMessage("Esse usuario não existe!");
             return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
                }
        
        
    }
       public  ResponseEntity<?> statususer(int userId) {
        UserModelo user = ur.findByIdUser(userId);
                if (user != null) {
                    Boolean Eneable =user.isEnabled();
                  return new ResponseEntity<Boolean>(Eneable, HttpStatus.OK);
                } else {
                  rm.setMessage("Esse usuario não existe!");
             return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
                }
        
        
    }

   
}
