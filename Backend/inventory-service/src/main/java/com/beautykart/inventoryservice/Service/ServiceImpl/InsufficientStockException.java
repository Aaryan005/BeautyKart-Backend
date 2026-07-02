package com.beautykart.inventoryservice.Service.ServiceImpl;

public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(String message) {
        super(message);
    }
}
