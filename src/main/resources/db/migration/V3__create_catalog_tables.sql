CREATE TABLE categories(
                            id        UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
                            parent_id UUID         REFERENCES categories(id) ON DELETE SET NULL,
                            name      VARCHAR(100) NOT NULL,
                            slug      VARCHAR(100) NOT NULL UNIQUE,
                            is_active BOOLEAN      NOT NULL DEFAULT true
);

CREATE INDEX idx_categories_slug      ON categories(slug);
CREATE INDEX idx_categories_parent_id ON categories(parent_id);

CREATE TYPE product_status AS ENUM ('DRAFT', 'ACTIVE', 'INACTIVE');

CREATE TABLE products(
                          id          UUID           PRIMARY KEY DEFAULT gen_random_uuid(),
                          category_id UUID           REFERENCES categories(id) ON DELETE SET NULL,
                          name        VARCHAR(255)   NOT NULL,
                          slug        VARCHAR(255)   NOT NULL UNIQUE,
                          description TEXT,
                          brand       VARCHAR(100),
                          status      VARCHAR(20)    NOT NULL DEFAULT 'DRAFT',
                          created_at  TIMESTAMPTZ    NOT NULL DEFAULT now(),
                          updated_at  TIMESTAMPTZ    NOT NULL DEFAULT now()
);

CREATE INDEX idx_products_slug        ON products(slug);
CREATE INDEX idx_products_status      ON products(status);
CREATE INDEX idx_products_category_id ON products(category_id);

CREATE TABLE product_variants(
                                  id              UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
                                  product_id      UUID         NOT NULL REFERENCES products(id) ON DELETE CASCADE,
                                  sku             VARCHAR(100) NOT NULL UNIQUE,
                                  attributes_json JSONB        NOT NULL DEFAULT '{}',
                                  price_amount    BIGINT       NOT NULL,
                                  currency        CHAR(3)      NOT NULL DEFAULT 'USD',
                                  weight_grams    INT,
                                  is_active       BOOLEAN      NOT NULL DEFAULT true,
                                  created_at      TIMESTAMPTZ  NOT NULL DEFAULT now(),
                                  updated_at      TIMESTAMPTZ  NOT NULL DEFAULT now());

CREATE INDEX idx_variants_product_id ON product_variants(product_id);
CREATE INDEX idx_variants_sku        ON product_variants(sku);
