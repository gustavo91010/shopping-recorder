package com.ajudaqui.recalldecompras.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="purchase_items")
public class PurchaseItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "purchase_id", nullable = false)
	private Purchase purchase;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	private int quantity;
	private int quantity_average;
	private int last_quantity;

	private BigDecimal price_total;
	private BigDecimal price_average;
	private BigDecimal last_price;


	
	
	

	public PurchaseItem(Purchase purchase, Product product, int quantity) {
		super();
		this.purchase = purchase;
		this.product = product;
		this.quantity = quantity;
		this.price_total= product.getPrice().multiply(new BigDecimal(quantity));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalValue() {
		return price_total;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.price_total = totalValue;
	}

	public int getQuantity_average() {
		return quantity_average;
	}

	public void setQuantity_average(int quantity_average) {
		this.quantity_average = quantity_average;
	}

	public BigDecimal getPrice_average() {
		return price_average;
	}

	public void setPrice_average(BigDecimal price_average) {
		this.price_average = price_average;
	}

	public int getLast_quantity() {
		return last_quantity;
	}

	public void setLast_quantity(int last_quantity) {
		this.last_quantity = last_quantity;
	}

	public BigDecimal getPrice_total() {
		return price_total;
	}

	public void setPrice_total(BigDecimal price_total) {
		this.price_total = price_total;
	}

	public BigDecimal getLast_price() {
		return last_price;
	}

	public void setLast_price(BigDecimal last_price) {
		this.last_price = last_price;
	}

	

}
