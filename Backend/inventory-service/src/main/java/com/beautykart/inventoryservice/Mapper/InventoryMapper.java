package com.beautykart.inventoryservice.Mapper;

import com.beautykart.inventoryservice.Entity.Inventory;
import com.beautykart.inventoryservice.dto.InventoryRequest;
import com.beautykart.inventoryservice.dto.InventoryResponse;
import com.beautykart.inventoryservice.dto.InventoryUpdateRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InventoryMapper {

    public static Inventory toEntity(InventoryRequest requeste) {
        Inventory inventory = new Inventory();

        inventory.setProductId(requeste.productId());
        inventory.setQuantity(requeste.quantity());
        inventory.setWarehouse(requeste.warehouse());

        return inventory;

    }

    public static InventoryResponse toResponse(Inventory inventory) {
        return new InventoryResponse(
                inventory.getInventoryId(),
                inventory.getProductId(),
                inventory.getQuantity(),
                inventory.getWarehouse()
        );
    }

    public static void updateEntity(Inventory inventory, InventoryUpdateRequest request) {
        inventory.setQuantity(request.quantity());
        inventory.setWarehouse(request.warehouse());
    }
}
