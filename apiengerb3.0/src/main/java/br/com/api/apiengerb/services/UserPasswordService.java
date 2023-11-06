package br.com.api.apiengerb.services;

import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.SecureRandomFactoryBean;
import org.springframework.security.core.token.Token;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.api.apiengerb.modelo.ClienteModelo;
import br.com.api.apiengerb.modelo.PasswordTokenPublicDTO;
import br.com.api.apiengerb.repositorio.ClienteRepositorio;
import br.com.api.apiengerb.repositorio.UserRepositorio;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class UserPasswordService {

    private final UserRepositorio ur;
    private final ClienteRepositorio cr;
    private final PasswordEncoder passwordEncoder;

    @SneakyThrows
    public String generateTokenCliente(ClienteModelo cliente) {
        KeyBasedPersistenceTokenService tokenService = getInstanceFor(cliente);

        Token token = tokenService.allocateToken(cliente.getEmailCliente());

        return token.getKey();
    }

    @SneakyThrows
    public void changePassword(String newPassword, String rawToken) {
        PasswordTokenPublicDTO publicDTO = readPublicDTO(rawToken);

        if(isExpired(publicDTO)) {
            throw new RuntimeException("Token expirado");
        }

        ClienteModelo cliente = cr.findByEmailCliente(publicDTO.email());
        
         if(cliente!=null){
    
             KeyBasedPersistenceTokenService tokenService = this.getInstanceFor(cliente);
             tokenService.verifyToken(rawToken);
     
             cliente.setSenhaUser(this.passwordEncoder.encode(newPassword));
             ur.save(cliente);
            }
    }

    private boolean isExpired(PasswordTokenPublicDTO publicDTO) {
        Instant createdAt = new Date(publicDTO.createAtTimestamp()).toInstant();
        Instant now = new Date().toInstant();
        return createdAt.plus(Duration.ofDays(1)).isBefore(now);
    }

    private KeyBasedPersistenceTokenService getInstanceFor(ClienteModelo cliente) throws Exception {
        KeyBasedPersistenceTokenService tokenService = new KeyBasedPersistenceTokenService();

        tokenService.setServerSecret(cliente.getPassword());
        tokenService.setServerInteger(16);
        tokenService.setSecureRandom(new SecureRandomFactoryBean().getObject());
        return tokenService;
    }

    private PasswordTokenPublicDTO readPublicDTO(String rawToken) {
        String rawTokenDecoded = new String(Base64.getDecoder().decode(rawToken));
        String[] tokenParts = rawTokenDecoded.split(":");
        Long timestamp = Long.parseLong(tokenParts[0]);
        String email = tokenParts[2];
        return new PasswordTokenPublicDTO(email, timestamp);
    }

}