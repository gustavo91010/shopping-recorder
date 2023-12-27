
CREATE TABLE IF NOT EXISTS purchases (
    id SERIAL PRIMARY KEY,
    purchase_date TIMESTAMP,
    total_value NUMERIC
);
