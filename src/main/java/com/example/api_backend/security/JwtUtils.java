package com.example.api_backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {

    private final String SECRET_KEY = "mi_clave_secreta_super_segura_para_el_backend_spring_boot";

    // Inyecta el valor definido en application.properties (por defecto 15 minutos si no existe)
    @Value("${jwt.expiration.minutes:15}")
    private long expirationMinutes;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    // Devuelve el tiempo de expiración configurado en milisegundos
    public long getExpirationMillis() {
        return expirationMinutes * 60 * 1000;
    }

    public String generateToken(String username, String rol) {
        long expirationTime = getExpirationMillis();
        return Jwts.builder()
                .subject(username)
                .claim("rol", rol)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey())
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    
    public Date getExpirationDateFromToken(String token) { 
    	return getClaims(token).getExpiration();
    }
}