CREATE TABLE carts (
                       id         UUID        PRIMARY KEY DEFAULT gen_random_uuid(),
                       user_id    UUID        REFERENCES users(id) ON DELETE CASCADE,
                       session_id VARCHAR(255) UNIQUE,
                       status     VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
                       created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
                       updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX idx_carts_user_id    ON carts(user_id);
CREATE INDEX idx_carts_session_id ON carts(session_id);
CREATE INDEX idx_carts_status     ON carts(status);

CREATE TABLE cart_items (
                            id                UUID        PRIMARY KEY DEFAULT gen_random_uuid(),
                            cart_id           UUID        NOT NULL REFERENCES carts(id) ON DELETE CASCADE,
                            variant_id        UUID        NOT NULL REFERENCES product_variants(id),
                            quantity          INT         NOT NULL CHECK (quantity > 0),
                            unit_price_amount BIGINT      NOT NULL,
                            currency          VARCHAR(3)  NOT NULL DEFAULT 'USD',
                            created_at        TIMESTAMPTZ NOT NULL DEFAULT now(),
                            updated_at        TIMESTAMPTZ NOT NULL DEFAULT now(),
                            UNIQUE(cart_id, variant_id)
);

CREATE INDEX idx_cart_items_cart_id ON cart_items(cart_id);