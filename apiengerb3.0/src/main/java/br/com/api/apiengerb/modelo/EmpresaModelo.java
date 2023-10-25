package br.com.api.apiengerb.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Declarando Modelo da Classe empresa e Criando tabela no banco de dados 
@Entity
@Table(name = "empresa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("EMPRESA")
public class EmpresaModelo extends UserModelo {

    //Definindo demais atributos da tabela
    @Column(name = "smtp_empresa", nullable = false, unique = true)
    @NotBlank(message = "O SMPT da Empresa não pode estar em branco")
    private String smtpEmpresa;
    @Column(name = "porta_empresa", nullable = false, unique = true)
    private Integer portaEmpresa;

    public EmpresaModelo(@NotBlank String login, @NotBlank String senhaUser, UserRole role, EmpresaModelo empresa,
            String nomeUser, @NotBlank String smtpEmpresa, @NotBlank Integer portaEmpresa, boolean isEnabled) {
        super(login, senhaUser, role, empresa, nomeUser, isEnabled);
        this.smtpEmpresa = smtpEmpresa;
        this.portaEmpresa = portaEmpresa;
    }
    
}
