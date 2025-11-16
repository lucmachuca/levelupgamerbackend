package com.levelupgamer.levelupbackend.exceptions;

// excepción usada cuando el cliente envía datos inválidos o incompletos
public class BadRequestException extends RuntimeException {
    public BadRequestException(String mensaje) {
        super(mensaje);
    }
}
