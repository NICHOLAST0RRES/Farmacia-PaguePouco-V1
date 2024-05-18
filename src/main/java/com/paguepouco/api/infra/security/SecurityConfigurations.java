package com.paguepouco.api.infra.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

// Essa classe define as configurações de segurança
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;


    // Essa define o filtro de segurança, esse define algumas caracteriscas que querendo nessa api
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return

                // desabilitando a proteção contra esse tipo de ataque(csrf)
                http.csrf(csrf -> csrf.disable())

                        //definindo a autentificação para ser STATELESS
                        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                                                                                                        /*Sobre a Anotation @Bean : serve para exportar uma classe para o Spring, fazendo com que  ele consiga
                                                      -------->                                                          carregá-la e realizar a sua injeção de dependência em outras classes. */
                        // liberando o acesso apenas para fazer login
                        .authorizeHttpRequests(req -> {
                            req.requestMatchers("/login").permitAll();

                        // definindo que qualquer outra requisição tem que estar autenticada
                            req.anyRequest().authenticated();
                        })
                        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();
    }

    // o Manager precisa desse metodo para poder fazer a injeção de dependencias
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // definindo para o Spring qual o Algoritimo de senha utilizado
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




}

