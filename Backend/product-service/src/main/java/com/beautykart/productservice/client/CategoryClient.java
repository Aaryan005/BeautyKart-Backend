package com.beautykart.productservice.client;

import com.beautykart.productservice.dto.CategoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "category-service",
        url = "http://localhost:8081"

)
public interface CategoryClient {

    @GetMapping("/api/categories/{categoryId}")
    CategoryResponse getCategoryById(@PathVariable Long categoryId);

}
