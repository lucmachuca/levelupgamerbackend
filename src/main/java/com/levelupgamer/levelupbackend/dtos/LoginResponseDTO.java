package com.levelupgamer.levelupbackend.dtos;

import com.levelupgamer.levelupbackend.models.Rol;
import lombok.Data;

// dto con la respuesta del login
@Data
public class LoginResponseDTO {

    private boolean exito;
    private String mensaje;

    // datos clave para manejar sesión en frontend
    private Long usuarioId;
    private String nombre;
    private Rol rol;
}
