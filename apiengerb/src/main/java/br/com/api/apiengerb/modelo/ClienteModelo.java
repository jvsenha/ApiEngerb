package br.com.api.apiengerb.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

//Declarando Modelo da Classe Cliente e Criando tabela no banco de dados 
@Entity
@Table(name = "cliente")
@Getter
@Setter
@DiscriminatorValue("CLIENTE")
public class ClienteModelo extends UserModelo{
    //Definindo atributo idCliente como chave da tabela
    @Column(unique=true)
    @NotBlank
    private String emailCliente;
    @Column(unique=true)
    @NotBlank
    private String pastaCliente;
    @NotBlank
    private UserRole role;
    
}
