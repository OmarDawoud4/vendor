package com.ecommerce.backend.catalog;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.UUID;


@Data
@Builder
public class VariantResponse {
    private UUID id ;
    private String sku ;
    private Long priceAmount  ;
    private String currency ;
    private Map<String , Object> attributes ;
    private boolean isActive ;

}
