package com.ecommerce.Shop_Backend.controller;

import com.ecommerce.Shop_Backend.dto.OrderRequest;
import com.ecommerce.Shop_Backend.service.impl.OrderServiceImpl;
import io.swagger.v3.oas.annotations.Parameter; // Add this import
import jakarta.validation.Valid; // Ensure you use validation
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;

    @PostMapping
    public ResponseEntity<?> placeOrder(
            @Valid @RequestBody OrderRequest request,
            @Parameter(hidden = true) Authentication authentication) { // Add @Parameter(hidden = true)

        String email = authentication.getName();
        return ResponseEntity.ok(orderService.placeOrder(request, email));
    }
}