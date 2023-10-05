package br.com.api.apiengerb.repositório;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.api.apiengerb.modelo.UserModelo;

public interface UserRepositorio extends JpaRepository <UserModelo, String>{
    
     UserDetails findByLogin(String usuarioUser);
}
