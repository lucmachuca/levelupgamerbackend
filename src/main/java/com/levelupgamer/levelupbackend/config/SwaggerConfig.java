package com.levelupgamer.levelupbackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

// configuración simple para habilitar documentación swagger / openapi
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Level Up Gamer API",
                version = "1.0",
                description = "Backend de la tienda Level Up Gamer"
        )
)
public class SwaggerConfig {
    // no requiere código adicional para esta configuración básica
}
