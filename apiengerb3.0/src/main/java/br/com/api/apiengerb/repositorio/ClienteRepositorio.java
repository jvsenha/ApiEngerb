package br.com.api.apiengerb.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.api.apiengerb.modelo.ClienteModelo;
import br.com.api.apiengerb.modelo.UserRole;




//definindo Para o Spring Que essa classe é Um Repository
@Repository
public interface ClienteRepositorio extends JpaRepository<ClienteModelo, Integer> {
   UserDetails findByIdUser(Integer idUser);
   ClienteModelo findByEmailCliente(String emailCliente);
   UserDetails findByPastaCliente(String pastaCliente);
   ClienteModelo findByLogin(String login);
   List<ClienteModelo> findByReset (Integer reset);
   UserDetails findByRole(UserRole role);
}
