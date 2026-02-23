package com.ecommerce.backend.catalog;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CreateProductRequest {

    @NotBlank(message = "Name is Required")
    private String name ;

    @NotBlank(message = "Slug is required")
    private String slug ;

    private String description ;
    private String brand ;
    private UUID categoryId ;

}
