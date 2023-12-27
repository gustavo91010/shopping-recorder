package com.ajudaqui.recalldecompras.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PurchaseItem {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "purchase_id", nullable = false)
	    private Purchase purchase;

	    @ManyToOne
	    @JoinColumn(name = "product_id", nullable = false)
	    private Product product;

	    private int quantity;
	    private BigDecimal totalValue;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Purchase getPurchase() {
			return purchase;
		}
		public void setPurchase(Purchase purchase) {
			this.purchase = purchase;
		}
		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public BigDecimal getTotalValue() {
			return totalValue;
		}
		public void setTotalValue(BigDecimal totalValue) {
			this.totalValue = totalValue;
		}
	    
	    
	
	
	
	

}
