package com.levelupgamer.levelupbackend.models;

import jakarta.persistence.*;
import lombok.*;

// entidad que representa un ítem dentro del carrito de un usuario
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carrito_items")
public class CarritoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // usuario dueño del item
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // producto agregado al carrito
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private Integer cantidad;

    // precio unitario al momento de agregarlo al carrito
    private Double precioUnitario;
}
