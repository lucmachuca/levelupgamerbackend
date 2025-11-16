package com.levelupgamer.levelupbackend.services;

import com.levelupgamer.levelupbackend.exceptions.ResourceNotFoundException;
import com.levelupgamer.levelupbackend.models.CarritoItem;
import com.levelupgamer.levelupbackend.models.Producto;
import com.levelupgamer.levelupbackend.models.Usuario;
import com.levelupgamer.levelupbackend.repositories.CarritoItemRepository;
import com.levelupgamer.levelupbackend.repositories.ProductoRepository;
import com.levelupgamer.levelupbackend.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// servicio con la lógica del carrito (agregar, listar, eliminar, vaciar)
@Service
@RequiredArgsConstructor
public class CarritoService {

    private final CarritoItemRepository carritoRepo;
    private final UsuarioRepository usuarioRepo;
    private final ProductoRepository productoRepo;

    public List<CarritoItem> obtenerCarrito(Long usuarioId) {
        Usuario u = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("usuario no encontrado"));
        return carritoRepo.findByUsuario(u);
    }

    public void agregarItem(Long usuarioId, Long productoId, Integer cantidad) {

        Usuario u = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("usuario no encontrado"));

        Producto p = productoRepo.findById(productoId)
                .orElseThrow(() -> new ResourceNotFoundException("producto no encontrado"));

        CarritoItem item = new CarritoItem(null, u, p, cantidad, p.getPrecio());
        carritoRepo.save(item);
    }

    public void eliminarItem(Long itemId) {
        CarritoItem item = carritoRepo.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("item no encontrado"));
        carritoRepo.delete(item);
    }

    public void vaciarCarrito(Long usuarioId) {
        Usuario u = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("usuario no encontrado"));
        carritoRepo.deleteByUsuario(u);
    }
}
