package com.example.api_backend.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_configurations")
public class AppConfig {

    @Id
    private Long id = 1L; // ID fijo para asegurar que solo exista un registro de configuración

    private Integer maxFileSizeMb;

    private Integer sessionDurationMins;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "config_selected_file_types", joinColumns = @JoinColumn(name = "config_id"))
    @Column(name = "file_type")
    private List<String> selectedFileTypes = new ArrayList<>();

    public AppConfig() {}

    public AppConfig(Integer maxFileSizeMb, Integer sessionDurationMins, List<String> selectedFileTypes) {
        this.id = 1L;
        this.maxFileSizeMb = maxFileSizeMb;
        this.sessionDurationMins = sessionDurationMins;
        this.selectedFileTypes = selectedFileTypes;
    }

    // Getters y Setters
    public Long getId() { 
    	return id; 
    }
    
    public void setId(Long id) {
    	this.id = id; 
	}

    public Integer getMaxFileSizeMb() {
    	return maxFileSizeMb; 
	}
    
    public void setMaxFileSizeMb(Integer maxFileSizeMb) {
    	this.maxFileSizeMb = maxFileSizeMb; 
	}

    public Integer getSessionDurationMins() {
    	return sessionDurationMins; 
	}
    
    public void setSessionDurationMins(Integer sessionDurationMins) {
    	this.sessionDurationMins = sessionDurationMins; 
	}

    public List<String> getSelectedFileTypes() {
    	return selectedFileTypes; 
	}
    
    public void setSelectedFileTypes(List<String> selectedFileTypes) {
    	this.selectedFileTypes = selectedFileTypes; 
	}
}