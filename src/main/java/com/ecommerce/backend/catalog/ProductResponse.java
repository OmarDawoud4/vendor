package com.ecommerce.backend.catalog;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
public class ProductResponse {

    private UUID id ;
    private String name;
    private String slug ;
    private String description ;
    private String brand ;
    private String status ;
    private UUID categoryId ;
    private String categoryName ;
    private OffsetDateTime createdAt ;


}
