package com.ajudaqui.recalldecompras.dto;

import java.math.BigDecimal;

public class RegisterProductDto {
	private String name;
	private String brand;
	private String average_unit;
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
	
	

}
