package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "email_verification_codes")
public class EmailVerificationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // email, на который отправлен код
    @Column(nullable = false)
    private String email;

    // код подтверждения (например 6 цифр)
    @Column(nullable = false)
    private String code;

    // время истечения кода
    @Column(nullable = false)
    private LocalDateTime expiresAt;

    // был ли код уже использован
    @Column(nullable = false)
    private boolean used = false;

    // --- constructors ---

    public EmailVerificationCode() {
    }

    public EmailVerificationCode(String email, String code, LocalDateTime expiresAt) {
        this.email = email;
        this.code = code;
        this.expiresAt = expiresAt;
        this.used = false;
    }

    // --- getters & setters ---

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public boolean isUsed() {
        return used;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
