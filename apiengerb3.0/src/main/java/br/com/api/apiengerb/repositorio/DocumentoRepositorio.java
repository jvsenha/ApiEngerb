package br.com.api.apiengerb.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.api.apiengerb.modelo.DocumentoModelo;

@Repository
public interface DocumentoRepositorio extends JpaRepository<DocumentoModelo, String> {
    // Você pode adicionar métodos de consulta personalizados aqui, se necessário
     List<DocumentoModelo> findByUsuario_IdUser(@Param("idUser") Integer idUser);
}