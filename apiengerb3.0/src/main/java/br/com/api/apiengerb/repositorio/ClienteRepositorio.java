package br.com.api.apiengerb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import br.com.api.apiengerb.modelo.ClienteModelo;
import br.com.api.apiengerb.modelo.UserRole;




//definindo Para o Spring Que essa classe é Um Repository
@Repository
public interface ClienteRepositorio extends JpaRepository<ClienteModelo, Integer> {
   UserDetails findByIdUser(Integer idUser);
   UserDetails findByEmailCliente(String emailCliente);
   UserDetails findByPastaCliente(String pastaCliente);
   UserDetails findByLogin(String login);
   UserDetails findByRole(UserRole role);
}
