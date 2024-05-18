package com.paguepouco.api.infra.security;

// essa classe é respondavel por gerar o Token JWT

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.paguepouco.api.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {

        try {

            // definindo a criptografia que vai ser ultilizada
            var algoritmo = Algorithm.HMAC256(secret);
            return
                    // criando o token JWT
                    JWT.create()

                    // identificando quem é Api responsavel pela geração do token
                    .withIssuer("paguePouco_api")

                    // identificando qual o usuario esta disparando a requisição, e por sua vez o dono do token
                    .withSubject(usuario.getLogin())

                    //instanciando a validade do token, usando o metodo "dataExpiracao"
                    .withExpiresAt(dataExpiracao())

                    //fazendo a assinatura do token
                    .sign(algoritmo);

            // capturando a exception e lançando uma mensaguem caso de erro
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar TokenJWT",exception);
        }

    }


    public String verificarToken(String TokenJWT){

        try {
            // definindo a criptografia que vai ser verificada
            var algoritmo = Algorithm.HMAC256(secret);

            return JWT.require(algoritmo)
            // veficando se é o autor da virificação
                    .withIssuer("paguePouco_api")


            // veficando o token e armazenado ele
            .build().verify(TokenJWT).getSubject();



        // capturando a exception e lançando uma mensaguem caso de erro
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token Invalido ou expirado",exception);
        }
    }


    // esse metodo cria a validade do Token
    private Instant dataExpiracao() {

            return
                    // pegando a data e hora atual
                    LocalDateTime.now().
                    // adicionando mais 2H(o tempo de validade do token)
                    plusHours(2).
                    // convertendo para Instant, uma vez que "withExpiresAt" recebe espera assim
                    toInstant
                    // setando o fuso horario brasileiro
                    (ZoneOffset.of("-03:00"));
    }


}