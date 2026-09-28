package com.example.api_backend.dto;

import java.util.List;

public class AppConfigDto {

    // Lista estática fija accesible a nivel de clase/DTO
    public static final List<String> AVAILABLE_FILE_TYPES = List.of(
        "image/png",
        "image/jpg",
        "image/jpeg",
        "image/webp",
        "image/svg+xml",
        "image/bmp",
        "image/tiff"
    );

    private Integer maxFileSizeMb;
    private List<String> availableFileTypes;
    private List<String> selectedFileTypes;
    private Integer sessionDurationMins;

    public AppConfigDto() {
        this.availableFileTypes = AVAILABLE_FILE_TYPES;
    }

    public AppConfigDto(Integer maxFileSizeMb, List<String> selectedFileTypes, Integer sessionDurationMins) {
        this.maxFileSizeMb = maxFileSizeMb;
        this.availableFileTypes = AVAILABLE_FILE_TYPES;
        this.selectedFileTypes = selectedFileTypes;
        this.sessionDurationMins = sessionDurationMins;
    }

    // Getters y Setters
    public Integer getMaxFileSizeMb() {
    	return maxFileSizeMb; 
    }
    
    public void setMaxFileSizeMb(Integer maxFileSizeMb) {
    	this.maxFileSizeMb = maxFileSizeMb; 
	}

    public List<String> getAvailableFileTypes() {
    	return availableFileTypes; 
	}

    public List<String> getSelectedFileTypes() {
    	return selectedFileTypes; 
	}
    
    public void setSelectedFileTypes(List<String> selectedFileTypes) {
    	this.selectedFileTypes = selectedFileTypes; 
	}

    public Integer getSessionDurationMins() {
    	return sessionDurationMins; 
	}
    
    public void setSessionDurationMins(Integer sessionDurationMins) {
    	this.sessionDurationMins = sessionDurationMins; 
	}
}