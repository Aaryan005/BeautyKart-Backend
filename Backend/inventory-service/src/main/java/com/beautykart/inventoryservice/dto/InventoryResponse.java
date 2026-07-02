package com.beautykart.inventoryservice.dto;

public record InventoryResponse(
        Long inventoryId,
        Long productId,
        Integer quantity,
        String warehouse
) {
}
