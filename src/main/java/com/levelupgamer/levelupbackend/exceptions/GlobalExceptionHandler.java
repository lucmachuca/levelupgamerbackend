package com.levelupgamer.levelupbackend.exceptions;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

// manejador global que captura excepciones y genera respuestas en formato JSON uniforme
@RestControllerAdvice
public class GlobalExceptionHandler {

    // recursos no encontrados
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(body(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    // errores de validación con @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleValidation(MethodArgumentNotValidException ex) {

        Map<String,Object> response = body(HttpStatus.BAD_REQUEST, "validación fallida");
        Map<String,String> errors = new LinkedHashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

        response.put("errors", errors);

        return ResponseEntity.badRequest().body(response);
    }

    // errores inesperados del servidor
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(body(HttpStatus.INTERNAL_SERVER_ERROR, "error interno del servidor"));
    }

    private Map<String,Object> body(HttpStatus status, String message) {
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("timestamp", Instant.now().toString());
        map.put("status", status.value());
        map.put("error", status.getReasonPhrase());
        map.put("message", message);
        return map;
    }
}
