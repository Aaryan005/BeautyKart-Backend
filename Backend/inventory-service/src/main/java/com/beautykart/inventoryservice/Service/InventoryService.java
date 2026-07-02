package com.beautykart.inventoryservice.Service;

import com.beautykart.inventoryservice.Entity.Inventory;
import com.beautykart.inventoryservice.dto.InventoryRequest;
import com.beautykart.inventoryservice.dto.InventoryResponse;
import com.beautykart.inventoryservice.dto.InventoryUpdateRequest;

import java.util.List;

public interface InventoryService {


    InventoryResponse createInventory(InventoryRequest request);

    List<InventoryResponse> getAllInventory();

    InventoryResponse getInventoryById(Long id);

    InventoryResponse getInventoryByProductId(Long productId);

    InventoryResponse updateInventory(Long id, InventoryUpdateRequest request);

    void deleteInventory(Long id);

    void reduceStock(Long productId, Integer quantity);
}
