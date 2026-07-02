package com.beautykart.orderservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

public record OrderRequest(
        @NotNull(message = "Customer ID is required")
        Long customerId,

        @Valid
        @NotNull(message = "Order items are required")
        List<OrderItemRequest> orderItems
) {}