package com.beautykart.productservice.dto;

import java.time.LocalDateTime;

public record CategoryResponse(

        Long categoryId,
        String categoryName,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}