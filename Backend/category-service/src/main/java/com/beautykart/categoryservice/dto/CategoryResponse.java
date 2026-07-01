package com.beautykart.categoryservice.dto;

public record CategoryResponse (Long categoryId, String name, String description,Boolean status) {
}
