package com.ecommerce.Shop_Backend.model;

import com.fasterxml.jackson.annotation.JsonFormat; // <--- MAKE SURE THIS IS IMPORTED
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class BaseEntity {

    @Column(updatable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") // <--- THIS FIXES SWAGGER DATE CRASHES
    private LocalDateTime createdAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") // <--- THIS TOO
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}