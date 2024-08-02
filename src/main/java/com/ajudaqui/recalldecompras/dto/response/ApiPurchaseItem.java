package com.ajudaqui.recalldecompras.dto.response;

import com.ajudaqui.recalldecompras.entity.PurchaseItem;

public class ApiPurchaseItem {
private PurchaseItem purchaseItem;



public ApiPurchaseItem(PurchaseItem purchaseItem) {
	super();
	this.purchaseItem = purchaseItem;
}

public PurchaseItem getPurchaseItem() {
	return purchaseItem;
}

public void setPurchaseItem(PurchaseItem purchaseItem) {
	this.purchaseItem = purchaseItem;
}



}
