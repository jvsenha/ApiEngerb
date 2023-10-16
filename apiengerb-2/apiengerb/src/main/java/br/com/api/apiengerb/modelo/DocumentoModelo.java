package br.com.api.apiengerb.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


//Declarando Modelo da Classe documento e Criando tabela no banco de dados 
@Entity
@Table(name = "documento")
@Getter
@Setter
public class DocumentoModelo {

    // Definindo atributo idDocumento como chave da tabela
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDocumento;

    // Chave estrangeira id_Cliente
    @ManyToOne
    @JoinColumn(name = " id_cliente")
    @NotBlank
    private UserModelo cliente;

    @Column(unique = true)
    @NotBlank
    private String nomeDocumento;
    private Integer tamanhoDocumento;
    @NotBlank
    private String linkDocumento;

}
