package com.ecommerce.Shop_Backend.service.impl;

import com.ecommerce.Shop_Backend.dto.LoginRequest;
import com.ecommerce.Shop_Backend.dto.RegisterRequest;
import com.ecommerce.Shop_Backend.model.Role;
import com.ecommerce.Shop_Backend.model.User;
import com.ecommerce.Shop_Backend.repository.UserRepository;
import com.ecommerce.Shop_Backend.service.AuthService;
import com.ecommerce.Shop_Backend.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public User register(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Secure password!
        user.setFullName(request.getFullName());
        user.setRole(Role.ROLE_USER);
        return userRepository.save(user);
    }

    @Override
    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtils.generateToken(user.getEmail());
    }
}