package com.ecommerce.backend.catalog;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminCatalogController {

    private final CatalogService catalogService  ;

    @PostMapping("/products")
    public ResponseEntity<ProductResponse>createProdcut (
            @Valid @RequestBody CreateProductRequest request
    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(catalogService.createProdcut(request));
    }

    @PostMapping("/prodcuts/{productId}/varaints")
    public ResponseEntity<VariantResponse> createVariant(
            @PathVariable UUID productId ,
            @Valid @RequestBody CreateVariantRequest request

    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(catalogService.createVariant(productId,request));
    }
}
