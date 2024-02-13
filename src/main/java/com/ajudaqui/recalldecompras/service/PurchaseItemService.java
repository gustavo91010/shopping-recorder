package com.ajudaqui.recalldecompras.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajudaqui.recalldecompras.dto.RegisterProductDto;
import com.ajudaqui.recalldecompras.entity.Product;
import com.ajudaqui.recalldecompras.entity.Purchase;
import com.ajudaqui.recalldecompras.entity.PurchaseItem;
import com.ajudaqui.recalldecompras.exception.MsgException;
import com.ajudaqui.recalldecompras.repository.PurchaseItensRepository;
import com.ajudaqui.recalldecompras.service.model.UpdateItemPurchase;

@Service
public class PurchaseItemService {

	@Autowired
	private PurchaseItensRepository purchaseItemRepository;

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private ProductService procudService;

	public PurchaseItem newItem(Long purchaseId, RegisterProductDto registerProductDto, Double quant) {

		Purchase purchase = purchaseService.findById(purchaseId);

		Product procudt = procudService.findSpecificProduct(registerProductDto.getName(),
				registerProductDto.getBrand());

		if (procudt == null) {

			procudt = procudService.registration(registerProductDto);
		}

		PurchaseItem item = new PurchaseItem(purchase, procudt, quant);
		item = purchaseItemRepository.save(item);

		purchase.getItems().add(item);

		// Precisa disso para atualizar o purchase mesmo? sera que não da pra por em
		// cascata?

		return new PurchaseItem(purchaseService.attPurchase(purchase), procudt, quant);

	}

	public PurchaseItem findById(Long id) {
		Optional<PurchaseItem> item = purchaseItemRepository.findById(id);
		if (item.isEmpty()) {
			throw new MsgException("Item não encontrado");
		}
		return item.get();

	}

//	calculate the average 
	public BigDecimal priceAverage(BigDecimal lastValue, BigDecimal currentValue) {
		if (lastValue == null || currentValue == null) {
			throw new MsgException("Não é possível calcular a média. Alguns dos valores são nulos.");
		}

		BigDecimal sum = lastValue.add(currentValue);

		return sum.divide(new BigDecimal("2"));

	}

	public Double quantityAverage(Double lastValue, Double currentValue) {
		if (lastValue == null || currentValue == null) {
			throw new MsgException("Não é possível calcular a média. Alguns dos valores são nulos.");
		}

		return Double.valueOf((lastValue + currentValue) / 2);

	}

	public PurchaseItem update(Long id, UpdateItemPurchase updateItemPurchase) {
		PurchaseItem item = findById(id);

		if (!updateItemPurchase.getName().isEmpty()) {
			item.getProduct().setName(updateItemPurchase.getName());
		}
		if (!updateItemPurchase.getBrand().isEmpty()) {
			item.getProduct().setBrand(updateItemPurchase.getBrand());
		}
		if (updateItemPurchase.getPrice() == null) {
			item.getProduct().setPrice(updateItemPurchase.getPrice());
		}

		item.setLast_price(item.getPrice_total());

		item.setPrice_average(priceAverage(item.getProduct().getPrice(), updateItemPurchase.getPrice()));

		if (updateItemPurchase.getQuantity() == null) {
			item.setQuantity(updateItemPurchase.getQuantity());
		}
		item.setLast_quantity(item.getQuantity());

		item.setQuantity_average(quantityAverage(item.getQuantity(), updateItemPurchase.getQuantity()));

		purchaseItemRepository.save(item);

		return item;

	}

	public void delete(Long id) {
		purchaseItemRepository.deleteById(id);
	}

}
