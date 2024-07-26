package com.ajudaqui.recalldecompras.dto.response;

import java.util.List;

import com.ajudaqui.recalldecompras.entity.Purchase;

public class ApiPurchases {
	private List<Purchase> purchases;

	public ApiPurchases(List<Purchase> purchases) {
		super();
		this.purchases = purchases;
	}


	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

}
