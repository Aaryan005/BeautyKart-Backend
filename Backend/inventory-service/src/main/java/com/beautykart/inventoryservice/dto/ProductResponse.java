package com.beautykart.inventoryservice.dto;

public record ProductResponse(
        Long id,
        String name,
        String description,
        String brand,
        Double price,
        Integer quantity,
        String imageUrl
) {
}