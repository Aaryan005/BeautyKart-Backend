package com.beautykart.categoryservice.service.impl;

import com.beautykart.categoryservice.dto.CategoryCreateRequest;
import com.beautykart.categoryservice.dto.CategoryResponse;
import com.beautykart.categoryservice.dto.CategoryUpdateRequest;
import com.beautykart.categoryservice.entity.Category;
import com.beautykart.categoryservice.exception.CategoryNotFoundException;
import com.beautykart.categoryservice.repository.CategoryRepository;
import com.beautykart.categoryservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse createCategory(CategoryCreateRequest request) {
        Category category = new Category();

        category.setCategoryId(request.categoryId());
        category.setCategoryName(request.name());
        category.setDescription(request.description());
        category.setStatus(request.status());

        Category savedCategory = categoryRepository.save(category);

        return new CategoryResponse(
                savedCategory.getCategoryId(),
                savedCategory.getCategoryName(),
                savedCategory.getDescription(),
                savedCategory.getStatus()
        );
    }

    @Override
    public CategoryResponse updateCategory(Long categoryId, CategoryUpdateRequest request) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new CategoryNotFoundException("Category With Id :"+categoryId+" Not Found."));

        category.setCategoryName(request.name());
        category.setDescription(request.description());
        category.setStatus(request.status());
        Category updatedCategory = categoryRepository.save(category);

        return new CategoryResponse(
                updatedCategory.getCategoryId(),
                updatedCategory.getCategoryName(),
                updatedCategory.getDescription(),
                updatedCategory.getStatus()
        );
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new CategoryNotFoundException("Category With Id :"+categoryId+" Not Found."));

        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> new CategoryResponse(
                        category.getCategoryId(),
                        category.getCategoryName(),
                        category.getDescription(),
                        category.getStatus()
                ))
                .toList();
    }

    @Override
    public CategoryResponse getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new CategoryNotFoundException("Category With Id :"+categoryId+" Not Found."));


        return new CategoryResponse(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getDescription(),
                category.getStatus()
        );
    }


}
