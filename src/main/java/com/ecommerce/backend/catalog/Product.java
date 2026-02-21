package com.ecommerce.backend.catalog;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category ;

    @Column(nullable = false)
    private String name ;

    @Column(nullable = false, unique = true)
    private String slug ;

    @Column(columnDefinition = "TEXT")
    private String description ;

    private String brand ;


    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ProductStatus status = ProductStatus.DRAFT;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @Builder.Default
    private List<ProductVariant> variants = new ArrayList<>();

    @CreationTimestamp
    @Column(name ="created_at", updatable = false)
    private OffsetDateTime createdAt ;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;



    public enum ProductStatus {
        DRAFT , ACTIVE , INACTIVE
    }
}
