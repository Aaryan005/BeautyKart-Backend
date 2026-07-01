package com.beautykart.categoryservice.service;

import com.beautykart.categoryservice.dto.CategoryCreateRequest;
import com.beautykart.categoryservice.dto.CategoryResponse;
import com.beautykart.categoryservice.dto.CategoryUpdateRequest;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(CategoryCreateRequest request);

    CategoryResponse updateCategory(Long categoryId, CategoryUpdateRequest request);

    void deleteCategory(Long categoryId);

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategoryById(Long categoryId);
}
