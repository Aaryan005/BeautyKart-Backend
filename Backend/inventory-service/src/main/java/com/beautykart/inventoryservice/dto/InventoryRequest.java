package com.beautykart.inventoryservice.dto;

public record InventoryRequest(
    Long productId,
    Integer quantity,
    String warehouse
) {
}
