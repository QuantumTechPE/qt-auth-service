package com.qt.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.time.Instant;

/**
 * Verificación rápida de disponibilidad a través del Ingress (QT-3).
 * A diferencia de /q/health, se publica bajo el prefijo de la API.
 */
@Path("/ping")
@Produces(MediaType.APPLICATION_JSON)
public class PingResource {

    @GET
    public Pong ping() {
        return new Pong("ok", Instant.now().toString());
    }

    public record Pong(String status, String timestamp) {
    }
}
