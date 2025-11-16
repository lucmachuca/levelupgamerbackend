package com.levelupgamer.levelupbackend.repositories;

import com.levelupgamer.levelupbackend.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// repositorio encargado de la persistencia de usuarios
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreo(String correo);
}
