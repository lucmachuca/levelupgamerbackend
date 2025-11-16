package com.levelupgamer.levelupbackend.dtos;

import com.levelupgamer.levelupbackend.models.Rol;
import lombok.Data;

// dto para devolver información de usuario sin exponer la contraseña
@Data
public class UsuarioDetalleDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private Integer edad;
    private Integer descuentoAplicado;
    private Rol rol;
}
