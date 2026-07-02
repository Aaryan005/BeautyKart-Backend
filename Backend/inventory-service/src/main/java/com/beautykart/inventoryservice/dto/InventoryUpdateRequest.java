package com.beautykart.inventoryservice.dto;

public record InventoryUpdateRequest (
         Integer quantity,
         String warehouse
){
}
