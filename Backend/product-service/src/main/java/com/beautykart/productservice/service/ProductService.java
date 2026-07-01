package com.beautykart.productservice.service;

import com.beautykart.productservice.dto.ProductRequest;

import com.beautykart.productservice.dto.ProductResponse;
import com.beautykart.productservice.dto.ProductUpdateRequest;

import java.util.List;

public interface ProductService {
    ProductResponse getProductById(Long id);
    ProductResponse createProduct(ProductRequest request);
    ProductResponse updateProduct(Long id, ProductUpdateRequest request);
    void deleteProduct(Long id);
    List<ProductResponse> getAllProducts();
    List<ProductResponse> getProductsByCategory(Long categoryId);
}
