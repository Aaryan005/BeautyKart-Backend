package com.beautykart.orderservice.dto;



public record ProductResponse(
        Long productId,
        String productName,
        Double price,
        Long categoryId
) {}