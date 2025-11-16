package com.levelupgamer.levelupbackend.repositories;

import com.levelupgamer.levelupbackend.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// repositorio para consultas y operaciones sobre productos
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByCategoria(String categoria);
}
