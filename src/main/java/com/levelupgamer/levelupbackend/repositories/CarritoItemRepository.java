package com.levelupgamer.levelupbackend.repositories;

import com.levelupgamer.levelupbackend.models.CarritoItem;
import com.levelupgamer.levelupbackend.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// repositorio que administra el carrito de cada usuario
public interface CarritoItemRepository extends JpaRepository<CarritoItem, Long> {

    List<CarritoItem> findByUsuario(Usuario usuario);

    void deleteByUsuario(Usuario usuario);
}
