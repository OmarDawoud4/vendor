CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TYPE user_role   AS ENUM ('CUSTOMER', 'ADMIN', 'SUPPORT');
CREATE TYPE user_status AS ENUM ('ACTIVE', 'BLOCKED');
CREATE TYPE address_type AS ENUM ('BILLING', 'SHIPPING');

CREATE TABLE users (
                       id            UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
                       email         VARCHAR(255) NOT NULL UNIQUE,
                       password_hash VARCHAR(255) NOT NULL,
                       full_name     VARCHAR(255) NOT NULL,
                       phone         VARCHAR(50),
                       role          user_role    NOT NULL DEFAULT 'CUSTOMER',
                       status        user_status  NOT NULL DEFAULT 'ACTIVE',
                       created_at    TIMESTAMPTZ  NOT NULL DEFAULT now(),
                       updated_at    TIMESTAMPTZ  NOT NULL DEFAULT now()
);

CREATE INDEX idx_users_email  ON users(email);
CREATE INDEX idx_users_status ON users(status);

CREATE TABLE addresses (
                           id          UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
                           user_id     UUID         NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                           type        address_type NOT NULL,
                           line1       VARCHAR(255) NOT NULL,
                           line2       VARCHAR(255),
                           city        VARCHAR(100) NOT NULL,
                           state       VARCHAR(100),
                           postal_code VARCHAR(20)  NOT NULL,
                           country     CHAR(2)      NOT NULL,
                           is_default  BOOLEAN      NOT NULL DEFAULT false,
                           created_at  TIMESTAMPTZ  NOT NULL DEFAULT now(),
                           updated_at  TIMESTAMPTZ  NOT NULL DEFAULT now()
);

CREATE INDEX idx_addresses_user_id ON addresses(user_id);
