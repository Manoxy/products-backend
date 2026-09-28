package com.example.api_backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200") // Permite peticiones desde tu app de Angular
public class HolaController {

    @GetMapping("/hola")
    public Map<String, String> saludar() {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("message", "¡Hola Mundo desde Spring Boot!");
        respuesta.put("status", "OK");
        return respuesta;
    }
}