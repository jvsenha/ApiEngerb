package br.com.api.apiengerb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.api.apiengerb.modelo.UserModelo;


// Definindo  como Repository
@Repository
public interface UserRepositorio extends JpaRepository <UserModelo, Integer>{
    // estendendo o JpaRepository e declarando findByLogin do UserDetails
     UserDetails findByLogin(String login);
    UserDetails findBySenhaUser(String senhaUser);
    
}
