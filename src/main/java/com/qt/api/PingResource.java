package com.qt.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.time.Instant;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Verificación rápida de disponibilidad a través del Ingress (QT-3), con la
 * versión desplegada (QT-4). A diferencia de /q/health, se publica bajo el
 * prefijo de la API.
 */
@Path("/ping")
@Produces(MediaType.APPLICATION_JSON)
public class PingResource {

    private final String service;
    private final String version;

    public PingResource(
            @ConfigProperty(name = "quarkus.application.name") String service,
            @ConfigProperty(name = "qt.version") String version) {
        this.service = service;
        this.version = version;
    }

    @GET
    public Pong ping() {
        return new Pong("ok", service, version, Instant.now().toString());
    }

    public record Pong(String status, String service, String version, String timestamp) {
    }
}
