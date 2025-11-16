package com.levelupgamer.levelupbackend.services;

import com.levelupgamer.levelupbackend.dtos.UsuarioDetalleDTO;
import com.levelupgamer.levelupbackend.dtos.UsuarioRegistroDTO;
import com.levelupgamer.levelupbackend.dtos.UsuarioRolUpdateDTO;
import com.levelupgamer.levelupbackend.exceptions.ResourceNotFoundException;
import com.levelupgamer.levelupbackend.models.Rol;
import com.levelupgamer.levelupbackend.models.Usuario;
import com.levelupgamer.levelupbackend.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

// servicio que maneja la lógica de usuarios (registro, listado, cambio de rol)
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // registro público: siempre crea usuarios con rol USER
    public UsuarioDetalleDTO registrar(UsuarioRegistroDTO dto) {

        Usuario u = new Usuario();
        u.setNombre(dto.getNombre());
        u.setApellido(dto.getApellido());
        u.setCorreo(dto.getCorreo());
        u.setEdad(dto.getEdad());
        u.setContrasena(passwordEncoder.encode(dto.getContrasena()));

        // aplica descuento si usa correo duocuc
        u.setDescuentoAplicado(
                dto.getCorreo().toLowerCase().endsWith("@duocuc.cl") ? 20 : 0
        );

        u.setRol(Rol.USER);

        Usuario guardado = usuarioRepository.save(u);

        return toDetalle(guardado);
    }

    // listado de todos los usuarios (uso interno para admin)
    public List<UsuarioDetalleDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::toDetalle)
                .toList();
    }

    // búsqueda interna por correo (login)
    public Usuario buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo).orElse(null);
    }

    // cambio de rol por parte de un admin
    public UsuarioDetalleDTO actualizarRol(Long id, UsuarioRolUpdateDTO dto) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("usuario no encontrado"));

        usuario.setRol(dto.getRol());

        Usuario guardado = usuarioRepository.save(usuario);
        return toDetalle(guardado);
    }

    // helper para convertir entidad a dto
    private UsuarioDetalleDTO toDetalle(Usuario u) {
        UsuarioDetalleDTO dto = new UsuarioDetalleDTO();
        dto.setId(u.getId());
        dto.setNombre(u.getNombre());
        dto.setApellido(u.getApellido());
        dto.setCorreo(u.getCorreo());
        dto.setEdad(u.getEdad());
        dto.setDescuentoAplicado(u.getDescuentoAplicado());
        dto.setRol(u.getRol());
        return dto;
    }
}
