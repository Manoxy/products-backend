package com.example.api_backend.controller;

import com.example.api_backend.dto.AppConfigDto;
import com.example.api_backend.service.AppConfigService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/config")
@CrossOrigin(origins = "http://localhost:4200")
public class ConfigController {

	private final AppConfigService configService;

    public ConfigController(AppConfigService configService) {
        this.configService = configService;
    }

    @GetMapping("/app-params")
    public ResponseEntity<AppConfigDto> getAppParameters() {
        return ResponseEntity.ok(configService.getConfig());
    }

    @PutMapping("/app-params")
    public ResponseEntity<AppConfigDto> updateAppParameters(@RequestBody AppConfigDto dto) {
        AppConfigDto updatedConfig = configService.updateConfig(dto);
        return ResponseEntity.ok(updatedConfig);
    }
}