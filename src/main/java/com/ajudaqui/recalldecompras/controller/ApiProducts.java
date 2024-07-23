package com.ajudaqui.recalldecompras.controller;

import java.util.List;

import com.ajudaqui.recalldecompras.entity.Product;

public class ApiProducts {
private List<Product> products;

public ApiProducts(List<Product> products) {
	super();
	this.products = products;
}

public List<Product> getProducts() {
	return products;
}

public void setProducts(List<Product> products) {
	this.products = products;
}


}
