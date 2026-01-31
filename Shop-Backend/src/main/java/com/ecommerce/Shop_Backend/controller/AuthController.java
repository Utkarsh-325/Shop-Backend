package com.ecommerce.Shop_Backend.controller;

import com.ecommerce.Shop_Backend.dto.LoginRequest;
import com.ecommerce.Shop_Backend.dto.RegisterRequest;
import com.ecommerce.Shop_Backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) { // Added @Valid
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest request) { // Added @Valid
        return ResponseEntity.ok(authService.login(request));
    }
}