package br.com.api.apiengerb.modelo;

import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

//Declarando Modelo da Classe RespostaModelo
@Component
@Getter
@Setter
public class RespostaModelo {
    private String message;
}
