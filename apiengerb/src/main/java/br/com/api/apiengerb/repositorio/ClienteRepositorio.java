package br.com.api.apiengerb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.api.apiengerb.modelo.ClienteModelo;

//definindo Para o Spring Que essa classe é Um Repository
@Repository
public interface ClienteRepositorio extends JpaRepository<ClienteModelo, Integer> {
    // estendendo o JpaRepository
}
