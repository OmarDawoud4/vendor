package com.ecommerce.backend.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {

    Optional<Cart> findByUserIdAndStatus(UUID userId, Cart.CartStatus status);
    Optional<Cart> findBySessionIdAndStatus(String sessionId, Cart.CartStatus status);
}