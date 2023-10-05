package br.com.api.apiengerb.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente")
    private Integer idCliente;
    @NotBlank
    private UserModelo nomeCliente;
    @Column(unique=true)
    @NotBlank
    private String emailCliente;
    @Column(unique=true)
    @NotBlank
    private UserModelo usuarioCliente;
    @NotBlank
    @Column(length = 32)
    private UserModelo senhaCliente;
    @Column(unique=true)
    @NotBlank
    private String pastaCliente;
    @NotBlank
    private UserRole role;
    
}
