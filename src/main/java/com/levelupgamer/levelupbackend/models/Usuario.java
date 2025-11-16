package com.levelupgamer.levelupbackend.models;

import jakarta.persistence.*;
import lombok.*;

// entidad que representa un usuario registrado en la plataforma
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;

    @Column(unique = true)
    private String correo;

    private String contrasena;
    private Integer edad;

    // porcentaje de descuento aplicado (ej: 20 si es duoc)
    private Integer descuentoAplicado;

    // url de la foto de perfil (en este backend se deja opcional)
    private String fotoPerfilUrl;

    // rol del usuario (ADMIN o USER)
    @Enumerated(EnumType.STRING)
    private Rol rol;
}
