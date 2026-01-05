package com.example.demo.repository;

import com.example.demo.entity.EmailVerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationCodeRepository
        extends JpaRepository<EmailVerificationCode, Long> {

    Optional<EmailVerificationCode> findByEmailAndCodeAndUsedFalse(
            String email,
            String code
    );

    void deleteByEmail(String email);
}
