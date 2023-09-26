package br.com.api.apiengerb.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

//Declarando Modelo da Classe Cliente e Criando tabela no banco de dados 
@Entity
@Table(name = "cliente")
@Getter
@Setter
public class ClienteModelo {
    //Definindo atributo idCliente como chave da tabela
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente")
    private Integer idCliente;
    private String nomeCliente;
    private String emailCliente;
    private String usuarioCliente;
    private String senhaCliente;
    private String pastaCliente;


    
}
