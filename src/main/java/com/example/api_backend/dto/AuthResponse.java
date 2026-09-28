package com.example.api_backend.dto;

public class AuthResponse {

	private String token;
    private String username;
    private String rol;
    private long expires; // Unix timestamp en milisegundos

    public AuthResponse(String token, String username, String rol, long expires) {
        this.token = token;
        this.username = username;
        this.rol = rol;
        this.expires = expires;
    }

    public AuthResponse(String token, String username, String rol) {
        this.token = token;
        this.username = username;
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

	public long getExpires() {
		return expires;
	}

	public void setExpires(long expires) {
		this.expires = expires;
	}
}