package com.beautykart.inventoryservice.Service.ServiceImpl;

import com.beautykart.inventoryservice.Client.ProductClient;
import com.beautykart.inventoryservice.Entity.Inventory;
import com.beautykart.inventoryservice.Mapper.InventoryMapper;
import com.beautykart.inventoryservice.Repository.InventoryRepository;
import com.beautykart.inventoryservice.Service.InventoryService;
import com.beautykart.inventoryservice.dto.InventoryRequest;
import com.beautykart.inventoryservice.dto.InventoryResponse;
import com.beautykart.inventoryservice.dto.InventoryUpdateRequest;
import com.beautykart.inventoryservice.Exception.ResourceNotFoundException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;


    private final ProductClient productClient;

    @Override
    public InventoryResponse createInventory(InventoryRequest request) {

        try {
            // Validate product exists
            productClient.getProductById(request.productId());
        } catch (FeignException.NotFound ex) {
            throw new ResourceNotFoundException(
                    "Product not found with id: " + request.productId()
            );
        }

        Inventory inventory = InventoryMapper.toEntity(request);
        Inventory savedInventory = inventoryRepository.save(inventory);

        return InventoryMapper.toResponse(savedInventory);
    }

    @Override
    public InventoryResponse getInventoryById(Long id) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Inventory not found with id: " + id));

        return InventoryMapper.toResponse(inventory);
    }

    @Override
    public InventoryResponse getInventoryByProductId(Long productId) {

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Inventory not found for product id: " + productId));

        return InventoryMapper.toResponse(inventory);
    }

    @Override
    public InventoryResponse updateInventory(Long id, InventoryUpdateRequest request) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Inventory not found with id: " + id));

        InventoryMapper.updateEntity(inventory, request);

        Inventory updatedInventory = inventoryRepository.save(inventory);

        return InventoryMapper.toResponse(updatedInventory);
    }

    @Override
    public void deleteInventory(Long id) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Inventory not found with id: " + id));

        inventoryRepository.delete(inventory);
    }

    @Override
    public List<InventoryResponse> getAllInventory() {

        return inventoryRepository.findAll()
                .stream()
                .map(InventoryMapper::toResponse)
                .toList();
    }

    @Override
    public void reduceStock(Long productId, Integer quantity) {

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Inventory not found for product id: " + productId));

        if (inventory.getQuantity() < quantity) {
            throw new InsufficientStockException("Insufficient stock for product id: " + productId);
        }

        inventory.setQuantity(inventory.getQuantity() - quantity);

        inventoryRepository.save(inventory);
    }
}