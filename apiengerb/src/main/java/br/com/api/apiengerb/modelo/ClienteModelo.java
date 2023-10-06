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

//Declarando Modelo da Classe Cliente e Criando tabela no banco de dados 
@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("CLIENTE")
public class ClienteModelo extends UserModelo {
    // Definindo atributos 
    @Column(unique = true)
    @NotBlank
    private String emailCliente;
    @Column(unique = true)
    @NotBlank
    private String pastaCliente;


    //Método Construtor
    public ClienteModelo(@NotBlank String login, @NotBlank String senhaUser, UserRole role, ClienteModelo cliente,
            String nomeUser, @NotBlank String emailCliente, @NotBlank String pastaCliente) {
        super(login, senhaUser, role, cliente, nomeUser);
        this.emailCliente = emailCliente;
        this.pastaCliente = pastaCliente;
    }

    
}
