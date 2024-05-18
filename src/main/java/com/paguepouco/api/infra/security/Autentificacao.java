package com.paguepouco.api.infra.security;


import com.paguepouco.api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


// clase de serviço de autentificação
@Service
                                // "UserDetailsService" diz para o Spring que essa classe é um serviço de autentificação
public class Autentificacao implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override  // esse metodo busca o usuario
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }
}
