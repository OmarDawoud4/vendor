package com.ecommerce.backend.catalog;



import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService ;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories (){
        return ResponseEntity.ok(catalogService.getCategories());
    }
    @GetMapping("/products")
    public ResponseEntity<Page<ProductResponse>> getProducts(

            @RequestParam(defaultValue = "0") int page ,
            @RequestParam(defaultValue = "20") int size
    ){

        Pageable pageable = PageRequest.of(page, size , Sort.by("createdAt").descending());
        return ResponseEntity.ok(catalogService.getActiveProducts(pageable));
    }

    @GetMapping("/products/{slug}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable String slug) {
        return ResponseEntity.ok(catalogService.getProductBySlug(slug));
    }

    @GetMapping("/products/{slug}/variants")
    public ResponseEntity<List<VariantResponse>> getVariants(@PathVariable String slug) {
        return ResponseEntity.ok(catalogService.getVariantsByProductSlug(slug));
    }
}
