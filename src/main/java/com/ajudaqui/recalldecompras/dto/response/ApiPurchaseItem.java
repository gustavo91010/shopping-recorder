package com.ajudaqui.recalldecompras.dto.response;

import java.util.List;

import com.ajudaqui.recalldecompras.entity.PurchaseItem;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ApiPurchaseItem {
	private Long id;
	private String name;
	private List<PurchaseItem> itens;
	private int totalItens;

	
	
	public ApiPurchaseItem(Long id, String name, List<PurchaseItem> itens) {
		super();
		this.id = id;
		this.name = name.replace("_" ," ");
		this.totalItens= itens.size();
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
	public int getTotalItens() {
		return totalItens;
	}
	public void setTotalItens(int totalItens) {
		this.totalItens = totalItens;
	}
	
	
	

}
