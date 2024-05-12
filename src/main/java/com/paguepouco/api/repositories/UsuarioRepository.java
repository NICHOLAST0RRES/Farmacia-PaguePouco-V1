package com.paguepouco.api.repositories;

import com.paguepouco.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


// é o método responsável por realizar a consulta do usuário no BDs
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);
}
