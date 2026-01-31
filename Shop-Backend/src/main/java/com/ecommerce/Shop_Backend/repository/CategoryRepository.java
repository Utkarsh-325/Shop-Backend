package com.ecommerce.Shop_Backend.repository;

import com.ecommerce.Shop_Backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // JpaRepository provides save(), findAll(), findById(), deleteById() out of the box.
}