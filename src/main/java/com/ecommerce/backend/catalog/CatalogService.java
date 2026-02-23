package com.ecommerce.backend.catalog;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CatalogService {
    private final ProductRepository  productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductVariantRepository variantRepository ;

    public Page<ProductResponse> getActiveProducts(Pageable pageable, UUID categoryId, String q){
        if (categoryId != null) {
            return productRepository
                    .findByCategoryIdAndStatus(categoryId, Product.ProductStatus.ACTIVE, pageable)
                    .map(this::toProductResponse);
        }
        if (q != null && !q.isBlank()){
            return productRepository
                    .findByStatusAndNameContainingIgnoreCase(Product.ProductStatus.ACTIVE, q, pageable)
                    .map(this::toProductResponse);

        }
        return productRepository
                .findByStatus(Product.ProductStatus.ACTIVE, pageable)
                .map(this::toProductResponse);
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
// ADMIN
    public ProductResponse createProdcut(CreateProductRequest request){
        if (productRepository.existsBySlug(request.getSlug())) {
            throw new IllegalArgumentException("Slug already in use: " + request.getSlug());
        }
        Product product = Product.builder()
                .name(request.getName())
                .slug(request.getSlug())
                .description(request.getDescription())
                .brand(request.getBrand())
                .status(Product.ProductStatus.DRAFT)
                .build();

        return toProductResponse(productRepository.save(product));
    }
    public VariantResponse createVariant (UUID productId , CreateVariantRequest request){
        Product product= productRepository.findById(productId)
                .orElseThrow(()-> new IllegalArgumentException("Product not found "));

        ProductVariant variant = ProductVariant.builder()
                .product(product)
                .sku(request.getSku())
                .priceAmount(request.getPriceAmount())
                .currency(request.getCurrency())
                .weightGrams(request.getWeightGrams())
                .attributesJson(request.getAttributes() != null ? request.getAttributes() : new java.util.HashMap<>())
                .build();

        return toVariantResponse(variantRepository.save(variant));
    }
    public ProductResponse updateProductStatus(UUID productId, Product.ProductStatus status){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.setStatus(status);
        return toProductResponse(productRepository.save(product));

    }

    public Category createCategory(CreateCategoryRequest request){
        if (categoryRepository.existsBySlug(request.getSlug())) {
            throw new IllegalArgumentException("Slug already in use: " + request.getSlug());
        }

        Category category = Category.builder()
                .name(request.getName())
                .slug(request.getSlug())
                .build();

        if (request.getParentId() != null) {

            Category parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("Parent category not found"));
            category.setParent(parent);
        }

        return categoryRepository.save(category);
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
