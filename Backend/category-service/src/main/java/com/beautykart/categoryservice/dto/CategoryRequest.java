package com.beautykart.categoryservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
        @NotNull
        Long categoryId,

        @NotBlank
        String name,

        @NotBlank
        String description,

        @NotNull
        Boolean status
) {}
