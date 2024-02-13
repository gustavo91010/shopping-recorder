-- V005__create_purchase_items_table.sql
CREATE TABLE IF NOT EXISTS purchase_items
(
   id SERIAL PRIMARY KEY,
   purchase_id BIGINT NOT NULL,
   product_id BIGINT NOT NULL,
   quantity INTEGER NOT NULL,
   quantity_average INTEGER NOT NULL,
   last_quantity INTEGER NOT NULL,
   price_total NUMERIC,
   price_average NUMERIC,
   last_price NUMERIC,
   
   FOREIGN KEY (purchase_id) REFERENCES purchases (id),
   FOREIGN KEY (product_id) REFERENCES products (id)
);