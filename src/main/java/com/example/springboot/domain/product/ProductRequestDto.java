package com.example.springboot.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequestDto(@NotBlank String name, @NotNull BigDecimal price) {
}
