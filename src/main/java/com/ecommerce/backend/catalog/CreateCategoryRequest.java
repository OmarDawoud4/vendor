package com.ecommerce.backend.catalog;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CreateCategoryRequest{
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Slug is required")
    private String slug;

    private UUID parentId;

}