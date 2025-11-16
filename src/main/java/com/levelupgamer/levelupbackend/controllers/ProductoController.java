package com.levelupgamer.levelupbackend.controllers;

import com.levelupgamer.levelupbackend.dtos.ProductoDTO;
import com.levelupgamer.levelupbackend.services.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// controlador que expone endpoints para listar y administrar productos
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService productoService;

    // obtener listado completo de productos
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listar() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    // obtener detalle de un producto por id
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> detalle(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    // crear un nuevo producto
    @PostMapping
    public ResponseEntity<ProductoDTO> crear(@RequestBody ProductoDTO dto) {
        return ResponseEntity.ok(productoService.crear(dto));
    }

    // actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizar(@PathVariable Long id, @RequestBody ProductoDTO dto) {
        return ResponseEntity.ok(productoService.actualizar(id, dto));
    }

    // eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
