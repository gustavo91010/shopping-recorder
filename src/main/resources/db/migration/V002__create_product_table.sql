CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    brand VARCHAR(255),
    measurement_unit VARCHAR(255),
    quantity NUMERIC,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    price NUMERIC
);