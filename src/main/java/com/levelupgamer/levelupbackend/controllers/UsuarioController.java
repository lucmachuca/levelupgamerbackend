package com.levelupgamer.levelupbackend.controllers;

import com.levelupgamer.levelupbackend.dtos.UsuarioDetalleDTO;
import com.levelupgamer.levelupbackend.dtos.UsuarioRolUpdateDTO;
import com.levelupgamer.levelupbackend.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// controlador usado por el admin para gestionar usuarios
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    // obtener listado de todos los usuarios (solo admin)
    @GetMapping
    public ResponseEntity<List<UsuarioDetalleDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    // actualizar rol de un usuario (por ejemplo USER -> ADMIN)
    @PutMapping("/{id}/rol")
    public ResponseEntity<UsuarioDetalleDTO> actualizarRol(
            @PathVariable Long id,
            @RequestBody UsuarioRolUpdateDTO dto
    ) {
        return ResponseEntity.ok(usuarioService.actualizarRol(id, dto));
    }
}
