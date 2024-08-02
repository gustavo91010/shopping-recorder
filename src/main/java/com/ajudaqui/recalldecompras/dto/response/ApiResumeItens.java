package com.ajudaqui.recalldecompras.dto.response;

import java.math.BigDecimal;
import java.util.List;

import com.ajudaqui.recalldecompras.dto.ItemResumeDTO;

public class ApiResumeItens {
	private Long id;
	private String name;
	private List<ItemResumeDTO> itens;
	private int totalItens;
	private BigDecimal totalValue;

	
	
	public ApiResumeItens(Long id, String name,BigDecimal totalValue, List<ItemResumeDTO> itens) {
		super();
		this.id = id;
		this.name = name.replace("_" ," ");
		this.totalItens= itens.size();
		this.totalValue= totalValue;
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
	public int getTotalItens() {
		return totalItens;
	}
	public void setTotalItens(int totalItens) {
		this.totalItens = totalItens;
	}
	public List<ItemResumeDTO> getItens() {
		return itens;
	}
	public void setItens(List<ItemResumeDTO> itens) {
		this.itens = itens;
	}
	public BigDecimal getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}
	
	
	

}
