package br.com.api.apiengerb.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

//Declarando Modelo da Classe empresa e Criando tabela no banco de dados 
@Entity
@Table(name = "empresa")
@Getter
@Setter
public class EmpresaModelo {

    //Definindo atributo idEmpressa como chave da tabela
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpresa;
    
    //Definindo demais atributos da tabela
    private String nomeEmpresa;
    private String smtpEmpresa;
    private Integer portaEmpresa;
    private String usuarioEmpresa;
    private String senhaEmpresa;



    
}
