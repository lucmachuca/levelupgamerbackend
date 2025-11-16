package com.levelupgamer.levelupbackend.dtos;

import com.levelupgamer.levelupbackend.models.Rol;
import lombok.Data;

// dto usado para que un admin cambie el rol de un usuario existente
@Data
public class UsuarioRolUpdateDTO {

    // rol deseado (ADMIN o USER)
    private Rol rol;
}
