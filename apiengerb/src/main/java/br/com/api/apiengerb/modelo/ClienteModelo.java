package br.com.api.apiengerb.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

//Declarando Modelo da Classe Cliente e Criando tabela no banco de dados 
@Entity
@Table(name = "cliente")
@Getter
@Setter
public class ClienteModelo extends UserModelo{
    //Definindo atributo idCliente como chave da tabela

    @GeneratedValue(strategy = GenerationType.IDENTITY) // ou a estratégia de geração apropriada
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(unique=true)
    @NotBlank
    private String emailCliente;
    @Column(unique=true)
    @NotBlank
    private String pastaCliente;
    @NotBlank
    private UserRole role;
    
}
