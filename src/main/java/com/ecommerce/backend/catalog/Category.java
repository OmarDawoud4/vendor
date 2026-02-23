package com.ecommerce.backend.catalog;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name ="categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="parent_id")
    private Category parent ;


    @Column(nullable = false)
    private String name ;

    @Column(nullable = false , unique = true)
    private String slug ;


    private boolean isActive = true ;

}
