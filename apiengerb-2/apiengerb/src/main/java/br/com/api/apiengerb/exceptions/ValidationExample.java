package br.com.api.apiengerb.exceptions;

import java.util.Set;

import br.com.api.apiengerb.modelo.ClienteModelo;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class ValidationExample {
    public static void main(String[] args) {
        // Crie um objeto Validator
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        // Crie um objeto de exemplo que será validado
        ClienteModelo cliente = new ClienteModelo();
        cliente.setEmailCliente(""); // Defina um valor que viola as regras de validação

        // Realize a validação
        Set<ConstraintViolation<ClienteModelo>> violations = validator.validate(cliente);

        // Itere sobre as violações de validação e imprima os messageTemplates
        for (ConstraintViolation<ClienteModelo> violation : violations) {
            String messageTemplate = violation.getMessageTemplate();
            System.out.println("messageTemplate: " + messageTemplate);
        }
    }
}