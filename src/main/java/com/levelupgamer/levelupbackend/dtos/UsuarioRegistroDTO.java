package com.levelupgamer.levelupbackend.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

// dto usado para el registro público de usuarios (siempre rol USER)
@Data
public class UsuarioRegistroDTO {

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @Email
    @NotBlank
    private String correo;

    @Size(min = 6, message = "la contraseña debe tener al menos 6 caracteres")
    private String contrasena;

    @Min(value = 18, message = "debes ser mayor de 18 años")
    private Integer edad;
}
