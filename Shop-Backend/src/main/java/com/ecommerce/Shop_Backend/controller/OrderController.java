package com.ecommerce.Shop_Backend.controller;

import com.ecommerce.Shop_Backend.dto.OrderRequest;
import com.ecommerce.Shop_Backend.model.Order;
import com.ecommerce.Shop_Backend.model.OrderItem;
import com.ecommerce.Shop_Backend.service.impl.OrderServiceImpl; // Import your specific service
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService; // Injecting your implementation class

    // --- SECURED: Place an Order ---
    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest request) {
        // 1. Get Email from Security Context
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        // 2. Call YOUR Service (It handles User lookup + Stock Logic)
        Order savedOrder = orderService.placeOrder(request, email);

        // 3. Return SAFE response (Manual Map to avoid infinite recursion crash)
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Order Placed Successfully!");
        response.put("orderId", savedOrder.getId());
        response.put("total", savedOrder.getTotalAmount());
        response.put("status", savedOrder.getStatus());

        return ResponseEntity.ok(response);
    }

    // --- SECURED: Get My Orders ---
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getMyOrders() {
        // 1. Get Email
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        // 2. Call YOUR Service
        List<Order> orders = orderService.getMyOrders(email);

        // 3. Build Safe Response
        List<Map<String, Object>> safeResponse = new ArrayList<>();

        for (Order order : orders) {
            Map<String, Object> orderMap = new HashMap<>();
            orderMap.put("id", order.getId());
            orderMap.put("date", order.getCreatedAt());
            orderMap.put("totalAmount", order.getTotalAmount());
            orderMap.put("status", order.getStatus());

            List<Map<String, Object>> itemsList = new ArrayList<>();
            for (OrderItem item : order.getOrderItems()) {
                Map<String, Object> itemMap = new HashMap<>();
                itemMap.put("productName", item.getProduct().getName());
                itemMap.put("quantity", item.getQuantity());
                itemMap.put("price", item.getPrice());
                itemsList.add(itemMap);
            }
            orderMap.put("items", itemsList);
            safeResponse.add(orderMap);
        }

        return ResponseEntity.ok(safeResponse);
    }
}