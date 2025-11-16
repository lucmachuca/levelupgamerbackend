package com.levelupgamer.levelupbackend.services;

import com.levelupgamer.levelupbackend.dtos.LoginRequestDTO;
import com.levelupgamer.levelupbackend.dtos.LoginResponseDTO;
import com.levelupgamer.levelupbackend.models.Usuario;
import com.levelupgamer.levelupbackend.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// servicio encargado de validar credenciales y preparar la respuesta de login
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDTO login(LoginRequestDTO request) {

        LoginResponseDTO response = new LoginResponseDTO();

        Usuario usuario = usuarioRepository.findByCorreo(request.getCorreo())
                .orElse(null);

        if (usuario == null) {
            response.setExito(false);
            response.setMensaje("correo no registrado");
            return response;
        }

        boolean ok = passwordEncoder.matches(request.getContrasena(), usuario.getContrasena());

        if (!ok) {
            response.setExito(false);
            response.setMensaje("contraseña incorrecta");
            return response;
        }

        // si llega aquí, el login fue exitoso
        response.setExito(true);
        response.setMensaje("login exitoso");
        response.setUsuarioId(usuario.getId());
        response.setNombre(usuario.getNombre());
        response.setRol(usuario.getRol());

        return response;
    }
}
