CREATE TABLE IF NOT EXISTS product_price_history (
    id SERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    created_at TIMESTAMP,
    update_date TIMESTAMP,
    price NUMERIC,
    FOREIGN KEY (product_id) REFERENCES products(id)
);
