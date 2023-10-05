package br.com.api.apiengerb.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
