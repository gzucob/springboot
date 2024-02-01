package com.example.springboot.domain.product;

import java.math.BigDecimal;

public record ProductResponseDto (String id, String name, BigDecimal price) {
    public ProductResponseDto (Product product){
        this(product.getId(), product.getName(), product.getPrice());
    }
}