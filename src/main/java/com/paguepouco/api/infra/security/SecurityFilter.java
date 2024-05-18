package com.paguepouco.api.infra.security;


import com.paguepouco.api.repositories.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// essa classe é responsavel por verificar se nas requisições ha um token de validação
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService service;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // armazenando o token numa variavel,
        var tokenJWT = recuperarToken(request);

        // verificando se ha token
        if (tokenJWT != null) {

            //fazendo verificação do token
            var subject = service.verificarToken(tokenJWT);

            // buscando o usuario usuario
            var usuario = repository.findByLogin(subject);

            // para autenticar precisamos devolver um objeto do tipo "UsernamePasswordAuthenticationToken", que representa um usuario logado no sistema
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            // autentificando o usuario para as requisições
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("Logado na requisição");
        }
        // se caso nao tiver vai bater na verificação do Spring que pede um token, se estiver o token e tiver Ok, vai chamar autorizar a soliçitação
        filterChain.doFilter(request, response);
    }

    // esse metodo recupera o token enviado pela requisição para ser verificado
    private String recuperarToken(HttpServletRequest request) {

        // armazenando o cabeçario numa variavel
        var cabecario = request.getHeader("Authorization");

        // retirando o Prefixo "Bearer"
        if (cabecario != null) {
            return cabecario.replace("Bearer ","");
        }

        return null;
    }



}
