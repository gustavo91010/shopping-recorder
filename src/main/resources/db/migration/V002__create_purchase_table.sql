-- V002__create_purchase_table.sql
CREATE TABLE IF NOT EXISTS purchases (
    id SERIAL PRIMARY KEY,
    users_id BIGINT REFERENCES users(id) NOT NULL,
    purchase_date TIMESTAMP NOT NULL,
    total_value NUMERIC NOT NULL
);
