package com.ajudaqui.recalldecompras.dto.response;

import com.ajudaqui.recalldecompras.entity.Purchase;

public class ApiPurchase {
	private Purchase purchase;
	private int totalItens;


	public ApiPurchase(Purchase purchase) {
		super();
		this.purchase = purchase;
		this.totalItens= purchase.getItems().size();
	}
	

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public int getTotalItens() {
		return totalItens;
	}

	public void setTotalItens(int totalItens) {
		this.totalItens = totalItens;
	}
	

	


	

}
