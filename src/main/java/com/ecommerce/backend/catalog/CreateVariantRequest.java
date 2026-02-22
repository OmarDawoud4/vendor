package com.ecommerce.backend.catalog;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class CreateVariantRequest {

    @NotBlank(message = "SKU is required")
    private String sku;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Long priceAmount;

    private String currency = "USD";
    private Integer weightGrams;
    private Map<String, Object> attributes;

}