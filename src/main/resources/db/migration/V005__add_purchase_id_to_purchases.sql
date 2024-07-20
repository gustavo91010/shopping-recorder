-- V006__add_purchase_id_to_purchases.sql
ALTER TABLE purchases
ADD COLUMN purchase_id BIGINT;

-- Adicione a chave estrangeira
ALTER TABLE purchases
ADD CONSTRAINT fk_purchase_items
FOREIGN KEY (purchase_id)
REFERENCES purchase_items(id);
