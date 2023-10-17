package br.com.api.apiengerb.modelo;

public record CadastroEmpDTO(String login, String senhaUser, UserRole role, EmpresaModelo empresa,
String nomeUser, String smtpEmpresa, Integer portaEmpresa) {
// Record Para cadastrar de AuthenticationController
}
    
