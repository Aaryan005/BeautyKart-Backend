package com.beautykart.productservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductUpdateRequest(
        @NotBlank(message = "Product name is required")
        String name,

        String description,

        @NotBlank(message = "Brand is required")
        String brand,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.01", message = "Price must be greater than 0")
        Double price,

        @NotNull(message = "Stock is required")
        Integer quantity,

        String imageUrl,

        @NotNull(message = "Category Id is required")
        Long categoryId

) {
}
