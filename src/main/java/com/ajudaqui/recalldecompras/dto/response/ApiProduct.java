package com.ajudaqui.recalldecompras.dto.response;

import com.ajudaqui.recalldecompras.entity.Product;

public class ApiProduct {
	private Product product;
	



	public ApiProduct(Product product) {
		super();
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	

}
