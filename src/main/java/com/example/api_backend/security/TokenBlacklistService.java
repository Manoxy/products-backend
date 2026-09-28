package com.example.api_backend.security;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlacklistService {

    // Contenedor thread-safe para almacenar tokens revocados y su timestamp de expiración
    private final Map<String, Long> blacklistedTokens = new ConcurrentHashMap<>();

    /**
     * Revoca un token añadiéndolo a la lista negra.
     * @param token Token JWT sin el prefijo "Bearer "
     * @param expiresAt Timestamp Unix (en milisegundos) en el que caduca el token
     */
    public void blacklistToken(String token, long expiresAt) {
        cleanExpiredTokens();
        blacklistedTokens.put(token, expiresAt);
    }

    /**
     * Comprueba si un token ha sido revocado.
     */
    public boolean isBlacklisted(String token) {
        cleanExpiredTokens();
        return blacklistedTokens.containsKey(token);
    }

    /**
     * Elimina automáticamente de la memoria los tokens que ya caducaron por tiempo.
     */
    private void cleanExpiredTokens() {
        long now = System.currentTimeMillis();
        blacklistedTokens.entrySet().removeIf(entry -> entry.getValue() < now);
    }
}