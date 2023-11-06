package br.com.api.apiengerb.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.api.apiengerb.modelo.ClienteModelo;
import br.com.api.apiengerb.modelo.EmpresaModelo;
import br.com.api.apiengerb.repositorio.UserRepositorio;

@Service
public class TokenService {


    @Value("${api.security.token.secret}")
    private String secret;

    private Set<String> invalidTokens = new HashSet<>();

    public String gerarToken(ClienteModelo cm) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("apiengerb.api")
                    .withSubject(cm.getLogin())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }

    }

    public String gerarTokenEmp(EmpresaModelo em) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("apiengerb.api")
                    .withSubject(em.getLogin())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }

    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("apiengerb.api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusDays(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public boolean isTokenBlacklisted(String tokenId) {
        return invalidTokens.contains(tokenId);
    }

    public void blacklistToken(String tokenId) {
        invalidTokens.add(tokenId);
    }
}
