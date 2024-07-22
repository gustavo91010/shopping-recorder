package com.ajudaqui.recalldecompras.dto.response;

import com.ajudaqui.recalldecompras.entity.Purchase;

public class ApiPurchase {
	private Purchase purchase;


	public ApiPurchase(Purchase purchase) {
		super();
		this.purchase = purchase;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	


	

}
