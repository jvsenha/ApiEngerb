package br.com.api.apiengerb.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//Declarando Modelo da Classe documento e Criando tabela no banco de dados 
@Entity
@Table(name = "documento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDocumento;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UserModelo usuario;
    
    @NotBlank
    private String nomeDocumento;

    @NotNull
    private Integer tamanhoDocumento;

    @NotBlank
    private String linkDocumento;

}
