package com.paguepouco.api.controller;


import com.paguepouco.api.dtos.Infra.DadosAutentificacao;
import com.paguepouco.api.dtos.Infra.DadosTokenJWT;
import com.paguepouco.api.infra.security.TokenService;
import com.paguepouco.api.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
public class AutentificacaoController {


    /* essa classe aciona o processo de autenficação, chamando a classe autentificação, porem ele não faz a sua propia,
    injeção  de dependencias, voce tem que faze-la, isso foi feito em "SecurityConfigurations" */
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService service;


    // essa classe chama o processo de autentificação atraves do "AuthenticationManager"
    @PostMapping
    public ResponseEntity autenticar(@Valid @RequestBody  DadosAutentificacao dados  ) { // recebe os dados de autentificação(login e senha)

        /* o manager so recebe de paramentro um objeto do tipo "UsernamePasswordAuthenticationToken", entao é peciso passar os dados de login para
         ele criando uma variavel*/
            var AutheticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());

            // armazenando o usuario autenticado dentro de uma variavel
           var autenticate =  manager.authenticate(AutheticationToken);

           // chamando o metodo de gerarToken, que esta dentro de "TokenService" e colocando numa variavel
           var TokenJTW = service.gerarToken((Usuario) autenticate.getPrincipal());

           // devolvendo o token dentro de dto propio para isso
           return ResponseEntity.ok(new DadosTokenJWT(TokenJTW));

    }
}
