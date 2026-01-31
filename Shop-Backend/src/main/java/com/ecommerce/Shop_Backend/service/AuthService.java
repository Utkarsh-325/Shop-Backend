package com.ecommerce.Shop_Backend.service;

import com.ecommerce.Shop_Backend.dto.LoginRequest;
import com.ecommerce.Shop_Backend.dto.RegisterRequest;
import com.ecommerce.Shop_Backend.model.User;

public interface AuthService {
    User register(RegisterRequest request);
    String login(LoginRequest request);
}