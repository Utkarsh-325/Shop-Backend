package com.ecommerce.Shop_Backend.repository;

import com.ecommerce.Shop_Backend.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Search by name (case-insensitive)
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // Filter by Category ID
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);
}