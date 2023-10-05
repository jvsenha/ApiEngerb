package br.com.api.apiengerb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.apiengerb.modelo.ClienteModelo;

@Repository
public interface ClienteRepositorio extends JpaRepository<ClienteModelo, Integer> {

}
