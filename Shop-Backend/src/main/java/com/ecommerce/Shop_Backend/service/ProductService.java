package com.ecommerce.Shop_Backend.service;

import com.ecommerce.Shop_Backend.dto.ProductRequest;
import com.ecommerce.Shop_Backend.model.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Product saveProduct(ProductRequest request);
    // Updated to return Page instead of List
    Page<Product> getAllProducts(int page, int size, String sortBy, String direction);
}