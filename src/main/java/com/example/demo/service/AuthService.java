package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EmailVerificationCode;
import com.example.demo.repository.EmailVerificationCodeRepository;

import java.time.LocalDateTime;
import java.util.Random;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailVerificationCodeRepository codeRepository;
    private final EmailService emailService;


    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       EmailVerificationCodeRepository codeRepository,
                       EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.codeRepository = codeRepository;
        this.emailService = emailService;
    }

    // ===== REGISTER BY EMAIL =====
    public void register(RegisterRequest request) {

        // 1. Проверяем email
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // 2. Создаём пользователя
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // 3. Сохраняем
        userRepository.save(user);
    }

    // ===== LOGIN BY EMAIL =====
    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return JwtUtil.generateToken(user.getEmail());
    }
    public void sendVerificationCode(String email) {

        // генерим 6-значный код
        String code = String.valueOf(100000 + new Random().nextInt(900000));

        EmailVerificationCode verificationCode = new EmailVerificationCode();
        verificationCode.setEmail(email);
        verificationCode.setCode(code);
        verificationCode.setExpiresAt(LocalDateTime.now().plusMinutes(5));
        verificationCode.setUsed(false);

        codeRepository.save(verificationCode);

        emailService.sendVerificationCode(email, code);
    }



}
