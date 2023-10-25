package br.com.api.apiengerb.modelo;

public record CadastroDTO(String login, String senhaUser, UserRole role, ClienteModelo cliente,
        String nomeUser, String emailCliente, String pastaCliente, boolean isEnabled ) {
    // Record Para cadastrar de AuthenticationController
}

