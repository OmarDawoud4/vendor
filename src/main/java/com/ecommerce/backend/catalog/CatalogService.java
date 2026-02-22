package com.ecommerce.backend.catalog;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CatalogService {
    private final ProductRepository  productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductVariantRepository variantRepository ;

    public Page<ProductResponse> getActiveProducts (Pageable pageable){

        return
                productRepository.findByStatus(Product.ProductStatus.ACTIVE , pageable)
                        .map(this ::toProductResponse);
    }

    public ProductResponse getProductBySlug (String slug ){
        Product product = productRepository.findBySlug(slug)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + slug));
        return toProductResponse(product);
    }
    public List<VariantResponse> getVariantsByProductSlug(String slug) {
        Product product = productRepository.findBySlug(slug)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + slug));
        return variantRepository.findByProductIdAndIsActiveTrue(product.getId())
                .stream()
                .map(this::toVariantResponse)
                .toList();
    }


public List<Category>getCategories (){
        return categoryRepository.findAll() ;
}
    private ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .slug(product.getSlug())
                .description(product.getDescription())
                .brand(product.getBrand())
                .status(product.getStatus().name())
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .createdAt(product.getCreatedAt())
                .build();
    }

    private VariantResponse toVariantResponse(ProductVariant variant) {
        return VariantResponse.builder()
                .id(variant.getId())
                .sku(variant.getSku())
                .priceAmount(variant.getPriceAmount())
                .currency(variant.getCurrency())
                .attributes(variant.getAttributesJson())
                .isActive(variant.isActive())
                .build();
    }

}
