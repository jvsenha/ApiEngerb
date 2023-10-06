package br.com.api.apiengerb.modelo;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public class UserModelo implements UserDetails {

    @Column(name = "idUser")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idUser;
    @NotBlank
    private String nomeUser;
    @Column(unique = true)
    @NotBlank
    private String login;
    @Column(length = 32)
    @NotBlank
    private String senhaUser;
    private UserRole role;

  
    public UserModelo(@NotBlank String login, @NotBlank String senhaUser, UserRole role, ClienteModelo cliente, String nomeUser) {
        this.login = login;
        this.senhaUser = senhaUser;
        this.role = role;
        this.nomeUser = nomeUser;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.EMP)
            return List.of(new SimpleGrantedAuthority("ROLE_EMP"), new SimpleGrantedAuthority("ROLE_USER"));
        else
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
     return senhaUser;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
       return true;
    }

    @Override
    public boolean isAccountNonLocked() {
       return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
    return true;
    }

    @Override
    public boolean isEnabled() {
       return true;
    }

}
