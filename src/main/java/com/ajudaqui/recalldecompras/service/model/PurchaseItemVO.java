package com.ajudaqui.recalldecompras.service.model;

import java.math.BigDecimal;

public class PurchaseItemVO {
	private Long purchaseId;
	private String name;
	private String brand;
	private String measurement_unit;
	private Double quantity_product;
	private Double quantity_items;
	private BigDecimal price;
	
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Long getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}
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
	public String getMeasurement_unit() {
		return measurement_unit;
	}
	public void setMeasurement_unit(String measurement_unit) {
		this.measurement_unit = measurement_unit;
	}
	public Double getQuantity_product() {
		return quantity_product;
	}
	public void setQuantity_product(Double quantity_product) {
		this.quantity_product = quantity_product;
	}
	public Double getQuantity_items() {
		return quantity_items;
	}
	public void setQuantity_items(Double quantity_items) {
		this.quantity_items = quantity_items;
	}
	
	

}
