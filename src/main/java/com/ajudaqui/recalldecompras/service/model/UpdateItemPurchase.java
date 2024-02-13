package com.ajudaqui.recalldecompras.service.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ajudaqui.recalldecompras.entity.Product;

public class UpdateItemPurchase {
	
	private String name;
	private String brand;
	private String average_unit;
	private BigDecimal price;
	private Double quantity;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getAverage_unit() {
		return average_unit;
	}
	public void setAverage_unit(String average_unit) {
		this.average_unit = average_unit;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	
	public Product extractProdut() {
		Product product = new Product();
		
		product.setName(this.name);
		product.setBrand(this.brand);
		product.setAverage_unit(this.average_unit);
		product.setPrice(this.price);
		
		product.setUpdated_at(LocalDateTime.now());
		return product;
	}
	
	

}
