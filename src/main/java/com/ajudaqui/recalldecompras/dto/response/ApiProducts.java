package com.ajudaqui.recalldecompras.dto.response;

import java.util.List;

import com.ajudaqui.recalldecompras.entity.Product;

public class ApiProducts {
private List<Product> products;
private int quantity;

public ApiProducts(List<Product> products) {
	super();
	this.products = products;
	this.quantity= products.size();
}

public List<Product> getProducts() {
	return products;
}

public int getQuantity() {
	return quantity;
}

}
