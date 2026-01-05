package com.example.demo.dto;

public class LoginResponse {

    private boolean success;
    private String message;


    public LoginResponse(String message) {
        this.success = success;
        this.message = message;
    }

    // getters
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
