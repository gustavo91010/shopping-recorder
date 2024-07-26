package com.ajudaqui.recalldecompras.dto.response;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.ajudaqui.recalldecompras.entity.Purchase;

public class PurchaseResponseAll {
	private List<PurchaseDTO> purchaseDTO= new ArrayList<>();

	
	public PurchaseResponseAll(List<Purchase> purchaseDTO) {
		super();
		this.purchaseDTO = purchaseDTO.stream()
				.sorted(Comparator.comparing(Purchase::getUpdated_at).reversed())
				.map(purchase-> new PurchaseDTO(purchase))
				.collect(Collectors.toList());
	}

	public List<PurchaseDTO> getPurchaseDTO() {
		return purchaseDTO;
	}

	public void setPurchaseDTO(List<PurchaseDTO> purchaseDTO) {
		this.purchaseDTO = purchaseDTO;
	}
	
	

}
