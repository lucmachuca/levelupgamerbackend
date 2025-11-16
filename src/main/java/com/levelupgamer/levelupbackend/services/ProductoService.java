package com.levelupgamer.levelupbackend.services;

import com.levelupgamer.levelupbackend.dtos.ProductoDTO;
import com.levelupgamer.levelupbackend.exceptions.ResourceNotFoundException;
import com.levelupgamer.levelupbackend.models.Producto;
import com.levelupgamer.levelupbackend.repositories.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// servicio que contiene la lógica de negocio asociada a productos
@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    public List<ProductoDTO> listarTodos() {
        return productoRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public ProductoDTO buscarPorId(Long id) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("producto no encontrado"));
        return toDTO(p);
    }

    public ProductoDTO crear(ProductoDTO dto) {
        Producto nuevo = productoRepository.save(toEntity(dto));
        return toDTO(nuevo);
    }

    public ProductoDTO actualizar(Long id, ProductoDTO dto) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("producto no encontrado"));

        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        p.setPrecio(dto.getPrecio());
        p.setImagenUrl(dto.getImagenUrl());
        p.setCategoria(dto.getCategoria());
        p.setCantidadDisponible(dto.getCantidadDisponible());

        return toDTO(productoRepository.save(p));
    }

    public void eliminar(Long id) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("producto no encontrado"));
        productoRepository.delete(p);
    }

    // conversión de entidad a dto
    private ProductoDTO toDTO(Producto p) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setDescripcion(p.getDescripcion());
        dto.setPrecio(p.getPrecio());
        dto.setImagenUrl(p.getImagenUrl());
        dto.setCategoria(p.getCategoria());
        dto.setCantidadDisponible(p.getCantidadDisponible());
        return dto;
    }

    // conversión de dto a entidad
    private Producto toEntity(ProductoDTO dto) {
        return new Producto(
                null,
                dto.getNombre(),
                dto.getDescripcion(),
                dto.getPrecio(),
                dto.getImagenUrl(),
                dto.getCategoria(),
                dto.getCantidadDisponible()
        );
    }
}
