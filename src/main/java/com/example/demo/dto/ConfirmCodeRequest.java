package com.example.demo.dto;

public class ConfirmCodeRequest {

    private String email;
    private String code;
    private String username;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getCode() {
        return code;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

