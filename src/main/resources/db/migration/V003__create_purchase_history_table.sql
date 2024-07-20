CREATE TABLE IF NOT EXISTS purchase_history (
    id SERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    quantidade BIGINT,
    price NUMERIC,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id)
);
