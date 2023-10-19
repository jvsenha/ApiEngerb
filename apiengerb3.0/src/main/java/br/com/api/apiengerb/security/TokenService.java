package br.com.api.apiengerb.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.api.apiengerb.modelo.ClienteModelo;
import br.com.api.apiengerb.modelo.EmpresaModelo;
import br.com.api.apiengerb.repositorio.UserRepositorio;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class TokenService {

    @Autowired
    private UserRepositorio ur;
    
    @Value("${api.security.token.secret}")
    private String secret;



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

    public boolean validarToken(HttpServletRequest request) {
        try{
        var authHeader = request.getHeader("Authorization");
       var token = authHeader.replace("Bearer ", "");
       
        if(token != null){
            var login = this.validateToken(token);
           
            UserDetails user = ur.findByLogin(login);
            
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
        }
        return true;
        } catch (JWTVerificationException exception) {
            // O token é inválido
            return false;
        }
        

       
    }
}
