package com.ajudaqui.recalldecompras.dto.response;

import java.util.List;

import com.ajudaqui.recalldecompras.entity.PurchaseItem;

public class ApiPurchaseItem {
	private Long id;
	private String name;
	private List<PurchaseItem> itens;
	
	
	public ApiPurchaseItem(Long id, String name, List<PurchaseItem> itens) {
		super();
		this.id = id;
		this.name = name;
		this.itens = itens;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<PurchaseItem> getItens() {
		return itens;
	}
	public void setItens(List<PurchaseItem> itens) {
		this.itens = itens;
	}
	
	

}
