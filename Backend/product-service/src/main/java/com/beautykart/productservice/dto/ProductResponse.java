package com.beautykart.productservice.dto;

import java.time.LocalDateTime;

public record ProductResponse(
        Long id,
        String name,
        String description,
        String brand,
        Double price,
        Integer quantity,
        String imageUrl,
        CategoryResponse category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
