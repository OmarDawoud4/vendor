package com.ecommerce.backend.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findBySlug(String slug);
    Page<Product> findByStatus(Product.ProductStatus status, Pageable pageable);
    Page<Product> findByCategoryIdAndStatus(UUID categoryId, Product.ProductStatus status, Pageable pageable);
    Page<Product> findByStatusAndNameContainingIgnoreCase(
            Product.ProductStatus status, String name, Pageable pageable);
    boolean existsBySlug(String slug);
}