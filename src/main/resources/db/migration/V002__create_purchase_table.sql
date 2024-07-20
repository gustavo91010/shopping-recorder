-- V002__create_purchase_table.sql
CREATE TABLE IF NOT EXISTS purchases (
    id SERIAL PRIMARY KEY,
    name VARCHAR (100) NOT NULL,
    users_id BIGINT REFERENCES users(id) NOT NULL,
    purchase_date TIMESTAMP NOT NULL,
    total_value NUMERIC NOT NULL
);
