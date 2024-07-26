package com.ajudaqui.recalldecompras.dto;

import java.math.BigDecimal;

import com.ajudaqui.recalldecompras.entity.PurchaseItem;

public class ItemResumeDTO {
		String name;
		String branch;
		double quantity;
		BigDecimal price;
		BigDecimal price_total;

		public ItemResumeDTO(PurchaseItem item) {
			super();
			this.name = item.getProduct().getName();
			this.branch = item.getProduct().getBrand();
			this.quantity = item.getQuantity();
			this.price = item.getProduct().getPrice();
			this.price_total = item.getPrice_total();
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getBranch() {
			return branch;
		}

		public void setBranch(String branch) {
			this.branch = branch;
		}

		public double getQuantity() {
			return quantity;
		}

		public void setQuantity(double quantity) {
			this.quantity = quantity;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public BigDecimal getPrice_total() {
			return price_total;
		}

		public void setPrice_total(BigDecimal price_total) {
			this.price_total = price_total;
		}

}
