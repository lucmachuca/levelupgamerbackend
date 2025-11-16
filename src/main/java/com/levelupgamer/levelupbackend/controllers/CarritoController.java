package com.levelupgamer.levelupbackend.controllers;

import com.levelupgamer.levelupbackend.models.CarritoItem;
import com.levelupgamer.levelupbackend.services.CarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// controlador que expone las operaciones del carrito
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "*")
public class CarritoController {

    private final CarritoService carritoService;

    // obtener todos los items del carrito de un usuario
    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<CarritoItem>> obtenerCarrito(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(carritoService.obtenerCarrito(usuarioId));
    }

    // agregar un producto al carrito de un usuario
    @PostMapping("/{usuarioId}/agregar")
    public ResponseEntity<Void> agregar(
            @PathVariable Long usuarioId,
            @RequestParam Long productoId,
            @RequestParam Integer cantidad) {

        carritoService.agregarItem(usuarioId, productoId, cantidad);
        return ResponseEntity.ok().build();
    }

    // eliminar un item específico del carrito
    @DeleteMapping("/item/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        carritoService.eliminarItem(id);
        return ResponseEntity.noContent().build();
    }

    // vaciar por completo el carrito de un usuario
    @DeleteMapping("/{usuarioId}/vaciar")
    public ResponseEntity<Void> vaciar(@PathVariable Long usuarioId) {
        carritoService.vaciarCarrito(usuarioId);
        return ResponseEntity.noContent().build();
    }
}
