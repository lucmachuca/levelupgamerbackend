package com.levelupgamer.levelupbackend.exceptions;

// excepción usada cuando un recurso no existe en la base de datos
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}
