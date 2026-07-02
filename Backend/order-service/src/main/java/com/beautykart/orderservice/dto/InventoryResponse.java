package com.beautykart.orderservice.dto;

public record InventoryResponse(
        Long inventoryId,
        Long productId,
        Integer quantity
) {}