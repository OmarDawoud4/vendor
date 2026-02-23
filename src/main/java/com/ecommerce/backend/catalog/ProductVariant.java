package com.ecommerce.backend.catalog;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "product_variants")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariant{


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false, unique = true)
    private String sku;


    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name ="attributes_json",columnDefinition = "jsonb")
    @Builder.Default
    private Map<String, Object> attributesJson =new java.util.HashMap<>();

    @Column(name = "price_amount",nullable = false)
    private Long priceAmount;

    @Column(nullable = false)
    @Builder.Default
    private String currency = "USD";

    private Integer weightGrams;

    @Builder.Default
    private boolean isActive = true;


    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

}