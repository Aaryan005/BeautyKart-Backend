package com.beautykart.productservice.service.impl;

import com.beautykart.productservice.client.CategoryClient;
import com.beautykart.productservice.dto.CategoryResponse;
import com.beautykart.productservice.dto.ProductRequest;
import com.beautykart.productservice.dto.ProductResponse;

import com.beautykart.productservice.dto.ProductUpdateRequest;
import com.beautykart.productservice.entity.Product;
import com.beautykart.productservice.exception.ResourceNotFoundException;
import com.beautykart.productservice.mapper.ProductMapper;
import com.beautykart.productservice.repository.ProductRepository;
import com.beautykart.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryClient categoryClient;

    @Override
    public ProductResponse createProduct(ProductRequest request) {

        CategoryResponse category = categoryClient.getCategoryById(request.categoryId());

        Product product = ProductMapper.toEntity(request);

        Product savedProduct = productRepository.save(product);

        return ProductMapper.toResponse(savedProduct, category);
    }
    @Override
    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(product -> { CategoryResponse category =
                        categoryClient.getCategoryById(product.getCategoryId());
                    return (ProductMapper.toResponse(product, category));
                })
                .toList();
    }
    @Override
    public ProductResponse getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id));
        CategoryResponse category =
                categoryClient.getCategoryById(product.getCategoryId());
        return ProductMapper.toResponse(product,category);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductUpdateRequest request) {

        CategoryResponse category =
                categoryClient.getCategoryById(request.categoryId());

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id));

        ProductMapper.updateEntity(product, request);

        Product updatedProduct = productRepository.save(product);

        return ProductMapper.toResponse(updatedProduct,category);
    }

    @Override
    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id));

        productRepository.delete(product);
    }

    @Override
    public List<ProductResponse> getProductsByCategory(Long categoryId) {

        CategoryResponse category = categoryClient.getCategoryById(categoryId);

        return productRepository.findByCategoryId(categoryId)
                .stream()
                .map(product -> ProductMapper.toResponse(product, category))
                .toList();
    }
}
