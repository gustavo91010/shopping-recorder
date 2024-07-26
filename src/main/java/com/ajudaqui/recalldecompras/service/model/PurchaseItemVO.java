package com.ajudaqui.recalldecompras.service.model;

import java.math.BigDecimal;

public class PurchaseItemVO {
	private String purchase_name;

	private String name;
	private String brand;
	private String measurement_unit;
	private Double quantity_product;
	private BigDecimal price;
	
	private Double quantity_items;
	
	public String getPurchase_name() {
		return purchase_name;
	}
	public void setPurchase_name(String purchase_name) {
		this.purchase_name = purchase_name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
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
	@Override
	public String toString() {
		return "PurchaseItemVO [purchase_name=" + purchase_name + ", name=" + name + ", brand=" + brand
				+ ", measurement_unit=" + measurement_unit + ", quantity_product=" + quantity_product
				+ ", quantity_items=" + quantity_items + ", price=" + price + "]";
	}
	
	

}
