package com.levelupgamer.levelupbackend.dtos;

import lombok.Data;

// dto para exponer datos de productos sin acoplar la entidad directamente
@Data
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagenUrl;
    private String categoria;
    private Integer cantidadDisponible;
}
