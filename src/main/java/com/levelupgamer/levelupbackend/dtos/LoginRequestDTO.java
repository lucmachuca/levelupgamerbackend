package com.levelupgamer.levelupbackend.dtos;

import lombok.Data;

// dto con las credenciales enviadas desde android / frontend
@Data
public class LoginRequestDTO {
    private String correo;
    private String contrasena;
}
