package com.beautykart.inventoryservice.Controller;


import com.beautykart.inventoryservice.Service.InventoryService;
import com.beautykart.inventoryservice.dto.InventoryRequest;
import com.beautykart.inventoryservice.dto.InventoryResponse;
import com.beautykart.inventoryservice.dto.InventoryUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryResponse> createInventory(@RequestBody InventoryRequest request){
        InventoryResponse response = inventoryService.createInventory(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponse> getInventoryById(@PathVariable Long id){
        InventoryResponse response = inventoryService.getInventoryById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getAllInventory() {
        List<InventoryResponse> response = inventoryService.getAllInventory();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponse> updateInventory(@PathVariable Long id, @RequestBody InventoryUpdateRequest request) {
        InventoryResponse response = inventoryService.updateInventory(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.ok("Inventory deleted successfully.");
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<InventoryResponse> getInventoryByProductId(@PathVariable Long productId) {
        InventoryResponse response = inventoryService.getInventoryByProductId(productId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/reduce/{productId}/{quantity}")
    public ResponseEntity<Void> reduceStock(
            @PathVariable Long productId,
            @PathVariable Integer quantity) {

        inventoryService.reduceStock(productId, quantity);
        return ResponseEntity.ok().build();
    }

}
