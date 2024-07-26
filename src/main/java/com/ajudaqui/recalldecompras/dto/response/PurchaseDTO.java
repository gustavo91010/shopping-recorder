package com.ajudaqui.recalldecompras.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ajudaqui.recalldecompras.entity.Purchase;

public class PurchaseDTO {
	 private Long id;
	    private Long userId;
	    private String name;
	    private LocalDateTime createdAt;
	    private LocalDateTime updatedAt;
	    private BigDecimal totalValue;
	    private int itemCount;
		public PurchaseDTO(Purchase purchase) {
			super();
			this.id = purchase.getId();
			this.userId = purchase.getUser_id();
			this.name =purchase.getName().replace("_", " ");
			this.createdAt = purchase.getCreated_at();
			this.updatedAt = purchase.getUpdated_at();
			this.totalValue = purchase.getTotalValue();
			this.itemCount = purchase.getItems().size();
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
		public LocalDateTime getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
		}
		public BigDecimal getTotalValue() {
			return totalValue;
		}
		public void setTotalValue(BigDecimal totalValue) {
			this.totalValue = totalValue;
		}
		public int getItemCount() {
			return itemCount;
		}
		public void setItemCount(int itemCount) {
			this.itemCount = itemCount;
		}
	    
		
	    
}
