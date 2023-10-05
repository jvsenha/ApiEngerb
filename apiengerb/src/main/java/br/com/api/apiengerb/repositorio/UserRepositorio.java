package br.com.api.apiengerb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.api.apiengerb.modelo.UserModelo;

@Repository
public interface UserRepositorio extends JpaRepository <UserModelo, Integer>{
    
     UserDetails findByLogin(String login);
}
