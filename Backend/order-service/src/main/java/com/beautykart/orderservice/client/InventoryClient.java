package com.beautykart.orderservice.client;

import com.beautykart.orderservice.dto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "inventory-service",
        url = "http://localhost:8083")
public interface InventoryClient {

    @GetMapping("/api/inventory/product/{productId}")
    InventoryResponse getInventoryByProductId(@PathVariable Long productId);

    @PutMapping("/api/inventory/reduce/{productId}/{quantity}")
    void reduceStock(@PathVariable Long productId,
                     @PathVariable Integer quantity);

}