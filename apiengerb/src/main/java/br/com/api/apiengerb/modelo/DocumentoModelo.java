package br.com.api.apiengerb.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "documento")
@Getter
@Setter
public class DocumentoModelo {

    //Definindo atributo idDocumento como chave da tabela
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDocumento;
    
    //Chave estrangeira id_Cliente
    @ManyToOne
    @JoinColumn(name = "id_Cliente")
    private ClienteModelo cliente;

    private String nomeDocumento;
    private Integer tamanhoDocumento; 
    private String linkDocumento;

}
