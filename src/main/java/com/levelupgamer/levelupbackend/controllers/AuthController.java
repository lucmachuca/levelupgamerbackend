package com.levelupgamer.levelupbackend.controllers;

import com.levelupgamer.levelupbackend.dtos.LoginRequestDTO;
import com.levelupgamer.levelupbackend.dtos.LoginResponseDTO;
import com.levelupgamer.levelupbackend.dtos.UsuarioDetalleDTO;
import com.levelupgamer.levelupbackend.dtos.UsuarioRegistroDTO;
import com.levelupgamer.levelupbackend.services.AuthService;
import com.levelupgamer.levelupbackend.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// controlador que maneja registro y login
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioService usuarioService;
    private final AuthService authService;

    // registro público de usuarios (siempre rol USER)
    @PostMapping("/registro")
    public ResponseEntity<UsuarioDetalleDTO> registrar(@Valid @RequestBody UsuarioRegistroDTO dto) {
        return ResponseEntity.ok(usuarioService.registrar(dto));
    }

    // login para android / frontend (devuelve id, nombre y rol)
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
