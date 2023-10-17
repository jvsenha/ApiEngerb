package br.com.api.apiengerb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import br.com.api.apiengerb.modelo.EmpresaModelo;

//definindo Para o Spring Que essa classe é Um Repository
@Repository
public interface EmpresaRepositorio extends JpaRepository<EmpresaModelo, Integer> {
    // estendendo o JpaRepository
    UserDetails findByIdUser(Integer idUser);
    UserDetails findBySmtpEmpresa(String smtpEmpresa);
    UserDetails findByPortaEmpresa(Integer portaEmpresa);
    UserDetails findByLogin(String login);
}
