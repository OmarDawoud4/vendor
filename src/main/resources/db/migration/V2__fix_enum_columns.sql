-- Remove defaults first, then change column types, then drop the enum types
ALTER TABLE users ALTER COLUMN role DROP DEFAULT;
ALTER TABLE users ALTER COLUMN status DROP DEFAULT;

ALTER TABLE users
ALTER COLUMN role TYPE VARCHAR(50) USING role::TEXT,
    ALTER COLUMN status TYPE VARCHAR(50) USING status::TEXT;

DROP TYPE user_role CASCADE;
DROP TYPE user_status CASCADE;
DROP TYPE address_type CASCADE;