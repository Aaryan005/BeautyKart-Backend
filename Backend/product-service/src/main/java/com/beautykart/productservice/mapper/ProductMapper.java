package com.beautykart.productservice.mapper;

import com.beautykart.productservice.dto.CategoryResponse;
import com.beautykart.productservice.dto.ProductRequest;
import com.beautykart.productservice.dto.ProductResponse;
import com.beautykart.productservice.dto.ProductUpdateRequest;
import com.beautykart.productservice.entity.Product;

public class ProductMapper {

    private ProductMapper() {
    }

    public static Product toEntity(ProductRequest request) {

        Product product = new Product();

        product.setName(request.name());
        product.setDescription(request.description());
        product.setBrand(request.brand());
        product.setPrice(request.price());
        product.setQuantity(request.quantity());
        product.setImageUrl(request.imageUrl());
        product.setCategoryId(request.categoryId());

        return product;
    }

    public static void updateEntity(Product product, ProductUpdateRequest request) {

        product.setName(request.name());
        product.setDescription(request.description());
        product.setBrand(request.brand());
        product.setPrice(request.price());
        product.setQuantity(request.quantity());
        product.setImageUrl(request.imageUrl());
        product.setCategoryId(request.categoryId());

    }

    public static ProductResponse toResponse(Product product, CategoryResponse category) {

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getBrand(),
                product.getPrice(),
                product.getQuantity(),
                product.getImageUrl(),
                category,
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

}