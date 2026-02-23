INSERT INTO categories (id, name, slug, is_active) VALUES
                                                       (gen_random_uuid(), 'Electronics', 'electronics', true),
                                                       (gen_random_uuid(), 'Clothing', 'clothing', true);

INSERT INTO products (id, name, slug, description, brand, status) VALUES
                                                                      (gen_random_uuid(), 'iPhone 15', 'iphone-15', 'Latest Apple smartphone', 'Apple', 'ACTIVE'),
                                                                      (gen_random_uuid(), 'Nike Air Max', 'nike-air-max', 'Classic running shoe', 'Nike', 'ACTIVE');