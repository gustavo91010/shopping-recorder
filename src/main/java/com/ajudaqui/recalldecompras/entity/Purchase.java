package com.ajudaqui.recalldecompras.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ajudaqui.recalldecompras.client.dto.UsersDTO;

@Entity
@Table(name = "purchases")
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long user_id;
	private String name;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	private BigDecimal totalValue;

	@OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PurchaseItem> items = new ArrayList<>();

	
	@Override
	public String toString() {
		return "Purchase [id=" + id + ","
				+ " user_id=" + user_id + ","
				+ " name=" + name + ","
				+ " created_at=" + created_at
				+ " updated_at=" + updated_at + ","
				+ " totalValue=" + totalValue + ","
				+ " items="
				+ items + "]";
	}
	public Purchase(String name, Long user_id) {
		this.name= name;
		this.user_id = user_id;
		this.created_at=LocalDateTime.now();
		this.updated_at=LocalDateTime.now();
		this.totalValue= BigDecimal.ZERO;
	}
	public Purchase() {
		// TODO Auto-generated constructor stub
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


	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}


	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public List<PurchaseItem> getItems() {
		return items;
	}

	public void setItems(List<PurchaseItem> items) {
		this.items = items;
	}

}
