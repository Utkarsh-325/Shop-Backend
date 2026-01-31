package com.ecommerce.Shop_Backend.config;

import com.ecommerce.Shop_Backend.model.Category;
import com.ecommerce.Shop_Backend.model.Product;
import com.ecommerce.Shop_Backend.repository.CategoryRepository; // Check your package name for Repository
import com.ecommerce.Shop_Backend.repository.ProductRepository;   // Check your package name for Repository
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;

    public DataSeeder(ProductRepository productRepo, CategoryRepository categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productRepo.count() == 0) {
            // 1. Create Category
            Category electronics = new Category();
            electronics.setName("Electronics");
            categoryRepo.save(electronics);

            // 2. Create Products
            Product p1 = new Product();
            p1.setName("iPhone 15");
            p1.setDescription("Latest Apple smartphone");
            p1.setPrice(new BigDecimal("999.99"));
            p1.setStockQuantity(10);
            p1.setCategory(electronics);

            Product p2 = new Product();
            p2.setName("Sony Headphones");
            p2.setDescription("Noise cancelling over-ear headphones");
            p2.setPrice(new BigDecimal("199.50"));
            p2.setStockQuantity(50);
            p2.setCategory(electronics);

            productRepo.saveAll(List.of(p1, p2));
            System.out.println("✅ DUMMY DATA ADDED!");
        }
    }
}