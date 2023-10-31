package br.com.api.apiengerb.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.api.apiengerb.modelo.UserModelo;


// Definindo  como Repository
@Repository
public interface UserRepositorio extends JpaRepository <UserModelo, Integer>{
    // estendendo o JpaRepository e declarando findByLogin do UserDetails
    List<UserModelo> findByIsEnabled(boolean isEnabled);
    UserModelo findByIdUser(int idUser);
     UserDetails findByLogin(String login);
    UserDetails findBySenhaUser(String senhaUser);
}
