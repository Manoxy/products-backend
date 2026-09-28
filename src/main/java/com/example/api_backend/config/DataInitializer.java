package com.example.api_backend.config;

import com.example.api_backend.entity.AppConfig;
import com.example.api_backend.entity.Rol;
import com.example.api_backend.entity.Usuario;
import com.example.api_backend.repository.UsuarioRepository;
import com.example.api_backend.repository.AppConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AppConfigRepository appConfigRepository;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {
            // Usuario Admin
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRol(Rol.ROLE_ADMIN);
            usuarioRepository.save(admin);

            // Usuario Estándar
            Usuario user = new Usuario();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRol(Rol.ROLE_USER);
            usuarioRepository.save(user);

            System.out.println(">>> Usuarios de prueba (admin y user) cargados en la base de datos.");
        }
        
        if (appConfigRepository.count() == 0) {
            AppConfig defaultConfig = new AppConfig(
                5,                                                  // maxFileSizeMb
                15,                                                 // sessionDurationMins
                List.of("image/png", "image/jpg", "image/jpeg")      // selectedFileTypes iniciales
            );
            appConfigRepository.save(defaultConfig);
        }
    }
}