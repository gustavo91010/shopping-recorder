-- V002__create_purchase_table.sql
CREATE TABLE IF NOT EXISTS purchases (
    id SERIAL PRIMARY KEY,
    name VARCHAR (100) NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    total_value NUMERIC NOT NULL
);
