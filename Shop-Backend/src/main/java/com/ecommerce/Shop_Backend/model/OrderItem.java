package com.ecommerce.Shop_Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore; // <--- Import this
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    @ToString.Exclude
    @Schema(hidden = true)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    // We usually keep Product visible so we know WHAT was bought,
    // but if Product has a list of OrderItems, you must @JsonIgnore this too.
    private Product product;

    private Integer quantity;
    private BigDecimal price;
}