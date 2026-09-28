package com.example.api_backend.exception;

public class ApiError {

    private String status;
    private String code;
    private int number;

    public ApiError() {
    }

    public ApiError(String status, String code, int number) {
        this.status = status;
        this.code = code;
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}