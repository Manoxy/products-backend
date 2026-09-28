package com.example.api_backend.service;

import com.example.api_backend.dto.AppConfigDto;
import com.example.api_backend.entity.AppConfig;
import com.example.api_backend.repository.AppConfigRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppConfigService {

    private final AppConfigRepository configRepository;

    public AppConfigService(AppConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @Transactional(readOnly = true)
    public AppConfigDto getConfig() {
        AppConfig config = configRepository.findById(1L)
                .orElseThrow(() -> new IllegalStateException("Configuración no inicializada"));

        return new AppConfigDto(
            config.getMaxFileSizeMb(),
            config.getSelectedFileTypes(),
            config.getSessionDurationMins()
        );
    }

    @Transactional
    public AppConfigDto updateConfig(AppConfigDto dto) {
        AppConfig config = configRepository.findById(1L)
                .orElseGet(() -> new AppConfig());

        // Filtrar y asegurar que solo se guarden tipos válidos dentro de los disponibles
        List<String> validTypes = dto.getSelectedFileTypes().stream()
                .filter(AppConfigDto.AVAILABLE_FILE_TYPES::contains)
                .toList();

        config.setMaxFileSizeMb(dto.getMaxFileSizeMb());
        config.setSessionDurationMins(dto.getSessionDurationMins());
        config.setSelectedFileTypes(validTypes);

        AppConfig saved = configRepository.save(config);

        return new AppConfigDto(
            saved.getMaxFileSizeMb(),
            saved.getSelectedFileTypes(),
            saved.getSessionDurationMins()
        );
    }
}
