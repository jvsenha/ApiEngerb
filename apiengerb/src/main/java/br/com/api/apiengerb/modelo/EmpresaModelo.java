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

//Declarando Modelo da Classe empresa e Criando tabela no banco de dados 
@Entity
@Table(name = "empresa")
@Getter
@Setter
public class EmpresaModelo extends UserModelo {

    //Definindo atributo idEmpressa como chave da tabela
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpresa;
    
    //Definindo demais atributos da tabela
    @Column(unique=true)
    @NotBlank
    private UserModelo nomeEmpresa;
    @NotBlank
    private String smtpEmpresa;
    @NotBlank
    private Integer portaEmpresa;
    @Column(unique=true)
    @NotBlank
    private UserModelo usuarioEmpresa;
    @NotBlank
    @Column(length = 32)
    private UserModelo senhaEmpresa;



    
}
