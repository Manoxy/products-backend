package com.example.api_backend.controller;

import com.example.api_backend.dto.AuthRequest;
import com.example.api_backend.dto.AuthResponse;
import com.example.api_backend.entity.Usuario;
import com.example.api_backend.exception.InvalidCredentialsException;
import com.example.api_backend.repository.UsuarioRepository;
import com.example.api_backend.security.JwtUtils;
import com.example.api_backend.security.TokenBlacklistService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String token = jwtUtils.generateToken(usuario.getUsername(), usuario.getRol().name());
        long expires = System.currentTimeMillis() + jwtUtils.getExpirationMillis(); // O el tiempo configurado

        return new AuthResponse(token, usuario.getUsername(), usuario.getRol().name(), expires);
    }
    
    @PostMapping("/refresh")
    public AuthResponse refreshToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new InvalidCredentialsException();
        }

        String token = authHeader.substring(7);

        // Extraemos el usuario y rol del token actual
        String username = jwtUtils.getUsernameFromToken(token);
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(InvalidCredentialsException::new);

        long newExpiresAt = System.currentTimeMillis() + jwtUtils.getExpirationMillis();
        String newToken = jwtUtils.generateToken(usuario.getUsername(), usuario.getRol().name());

        return new AuthResponse(newToken, usuario.getUsername(), usuario.getRol().name(), newExpiresAt);
    }
    
    @Autowired
    private TokenBlacklistService blacklistService;

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            
            // Extraemos la fecha de expiración del propio token para saber cuánto tiempo mantenerlo en la lista
            long expiresAt = jwtUtils.getExpirationDateFromToken(token).getTime();
            
            // Inhabilitamos el token en el servidor
            blacklistService.blacklistToken(token, expiresAt);
        }

        return ResponseEntity.ok().build();
    }
    
    
}