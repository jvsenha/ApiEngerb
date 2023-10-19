package br.com.api.apiengerb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration // Definindo como Classe de configuração
@EnableWebSecurity // Desativando padrão do Spring Security
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    // Criando Bean de filtro de segurança
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/logout").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/validarToken").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/loginemp").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/cadastrar").hasRole("EMP")
                        .requestMatchers(HttpMethod.POST, "/auth/cadastraremp").hasRole("EMP")
                        .requestMatchers(HttpMethod.GET, "/cliente/carregar/{idUser}").hasRole("EMP")
                        .requestMatchers(HttpMethod.GET, "/cliente/listar").hasRole("EMP")
                        .requestMatchers(HttpMethod.PUT, "/cliente/alterar/{idUser}").hasRole("EMP")
                        .requestMatchers(HttpMethod.DELETE, "/cliente/remover/{idCliente}").hasRole("EMP")
                        .anyRequest().authenticated()

                )
                .logout(logout -> logout
                .logoutSuccessUrl("/logout") // URL para efetuar logout
                .logoutSuccessHandler(logoutSuccessHandler()) // Manipulador de sucesso de logout
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public HttpStatusReturningLogoutSuccessHandler logoutSuccessHandler() {
        return new HttpStatusReturningLogoutSuccessHandler();
    }
    // Criando Bean de filtro de CORS

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000"); // Domínio do seu frontend
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    // Criando Bean de filtro de authenticationManager usado na
    // AuthenticationController
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Criando Bean para Hash da senha usado na AuthenticationController
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
