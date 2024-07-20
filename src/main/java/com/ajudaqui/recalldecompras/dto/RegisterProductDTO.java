package com.ajudaqui.recalldecompras.dto;

import java.math.BigDecimal;

public class RegisterProductDTO {
	private String name;
	private String brand;
	private String measurement_unit;
	private Double quantity;

	private BigDecimal price;
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
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	

}
