package br.com.api.apiengerb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import br.com.api.apiengerb.repositorio.UserRepositorio;

//Declarando Classe como Service, essa é usado para autenticação
@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    UserRepositorio repository;

    // Declarando método para Autenticar login
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

}
