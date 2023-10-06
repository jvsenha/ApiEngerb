package br.com.api.apiengerb.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

    private final PasswordEncoder delegate = new BCryptPasswordEncoder();

    // Tamanho máximo desejado para o hash
    private final int maxHashLength = 32;

    @Override
    public String encode(CharSequence rawPassword) {
        // Gere o hash
        String encodedPassword = delegate.encode(rawPassword);
        
        // Verifique o tamanho do hash e, se for maior que o tamanho máximo,
        // corte-o para o tamanho máximo desejado
        if (encodedPassword.length() > maxHashLength) {
            encodedPassword = encodedPassword.substring(0, maxHashLength);
        }
        
        return encodedPassword;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return delegate.matches(rawPassword, encodedPassword);
    }
}
