package com.ecommerce.Shop_Backend.controller;

import com.ecommerce.Shop_Backend.dto.ProductRequest;
import com.ecommerce.Shop_Backend.model.Product;
import com.ecommerce.Shop_Backend.repository.ProductRepository;
import com.ecommerce.Shop_Backend.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long categoryId,
            @org.springdoc.core.annotations.ParameterObject Pageable pageable) { // Added this annotation

        if (name != null && !name.isEmpty()) {
            return ResponseEntity.ok(productRepository.findByNameContainingIgnoreCase(name, pageable));
        }
        if (categoryId != null) {
            return ResponseEntity.ok(productRepository.findByCategoryId(categoryId, pageable));
        }
        return ResponseEntity.ok(productRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.saveProduct(request));
    }
}