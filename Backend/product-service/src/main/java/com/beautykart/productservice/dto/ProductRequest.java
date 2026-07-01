package com.beautykart.productservice.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(

        @NotBlank(message = "Product name is required")
         String name,


         String description,

         @NotBlank(message = "Brand is required")
         String brand,

         @NotNull(message = "Price is required")
         @Min(value = 1,message = "Price must be greater than 0")
         Double price,

         @NotNull(message = "Quantity is required")
         @Min(value = 0,message = "Quantity cant be negative")
         Integer quantity,

         String imageUrl,

         @NotNull(message = "Category ID is required")
         Long categoryId
) {
}
