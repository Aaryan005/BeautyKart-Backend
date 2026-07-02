package com.beautykart.productservice.controller;

import com.beautykart.productservice.dto.ProductRequest;
import com.beautykart.productservice.dto.ProductResponse;
import com.beautykart.productservice.dto.ProductUpdateRequest;
import com.beautykart.productservice.entity.Product;

import com.beautykart.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;  

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@Valid @RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse updateProductById(@PathVariable Long id, @Valid @RequestBody ProductUpdateRequest request) {
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }


    @GetMapping("/category/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getProductsByCategoryId(@PathVariable Long categoryId) {
        return productService.getProductsByCategory(categoryId);
    }
}
