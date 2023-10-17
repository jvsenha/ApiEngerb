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

//Declarando Modelo da Classe user e Criando tabela no banco de dados 
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Declarando Classe Mão de Cliente e Empresa
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING) // Declarando coluna para separar
                                                                                       // tabela dependente(Cliente ou
                                                                                       // empresa)
public class UserModelo implements UserDetails {

    @Column(name = "idUser")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @Column(name = "nome_user", length = 100, nullable = false, unique = true)
    @NotBlank(message = "O nome do Usuario não pode estar em branco")
    private String nomeUser;

    @Column(name = "login", length = 100, nullable = false, unique = true)
    @NotBlank(message = "O Login não pode estar em branco")
    private String login;

    @Column(name = "senha_user", nullable = false)
    @NotBlank(message = "A Senha não pode estar em branco")
    private String senhaUser;
    private UserRole role;

    // Declarando Método Construtor
    public UserModelo(@NotBlank String login, @NotBlank String senhaUser, UserRole role, ClienteModelo cliente,
            String nomeUser) {
        this.login = login;
        this.senhaUser = senhaUser;
        this.role = role;
        this.nomeUser = nomeUser;

    }

    public UserModelo(@NotBlank String login, @NotBlank String senhaUser, UserRole role, EmpresaModelo empresa,
            String nomeUser) {
        this.login = login;
        this.senhaUser = senhaUser;
        this.role = role;
        this.nomeUser = nomeUser;

    }

    // Declarando Métodos de UserDetails
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
