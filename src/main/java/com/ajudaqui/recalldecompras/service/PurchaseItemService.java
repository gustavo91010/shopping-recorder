package com.ajudaqui.recalldecompras.service;

import static java.lang.String.format;

import java.math.BigDecimal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajudaqui.recalldecompras.dto.RegisterProductDTO;
import com.ajudaqui.recalldecompras.entity.Product;
import com.ajudaqui.recalldecompras.entity.Purchase;
import com.ajudaqui.recalldecompras.entity.PurchaseItem;
import com.ajudaqui.recalldecompras.exception.MsgException;
import com.ajudaqui.recalldecompras.repository.PurchaseItensRepository;
import com.ajudaqui.recalldecompras.service.model.PurchaseItemVO;
import com.ajudaqui.recalldecompras.service.model.UpdateItemPurchaseVO;

@Service
public class PurchaseItemService {
	Logger logger = LoggerFactory.getLogger(PurchaseItemService.class);

	@Autowired
	private PurchaseItensRepository purchaseItemRepository;

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private ProductService procudService;

	public Purchase newItem(PurchaseItemVO purchaseItemVO) {
		logger.info(format("Adicionando produto %s a compra id %d", purchaseItemVO.getName(),
				purchaseItemVO.getPurchaseId()));
		Purchase purchase = purchaseService.findById(purchaseItemVO.getPurchaseId());

		Product procudt = findOldOrRegisterNewProduct(purchaseItemVO);


		// Verificar se o produto já existe na lista, incrementa
		for (PurchaseItem item : purchase.getItems()) {
			if (item.getProduct().equals(procudt)) {
				logger.info(format("Incrementando item já existente na lista"));

				Double quantityItem = item.getQuantity();
				quantityItem = quantityItem + purchaseItemVO.getQuantity_items();

				item.setQuantity(quantityItem);
				item.setPrice_total(attTotalPrice(quantityItem, item.getProduct().getPrice()));

				purchaseItemRepository.save(item);

				return purchase;
			}
		}

		PurchaseItem item = new PurchaseItem(purchase, procudt, purchaseItemVO.getQuantity_items());
		logger.info(format("Criando novo item."));

		item = purchaseItemRepository.save(item);
		purchase.getItems().add(item);

		return purchase;
	}



	private PurchaseItem findById(Long id) {
		Optional<PurchaseItem> item = purchaseItemRepository.findById(id);
		if (item.isEmpty()) {
			throw new MsgException("Item não encontrado");
		}
		return item.get();

	}

	// Atualizando total item
	private BigDecimal attTotalPrice(Double quanrity, BigDecimal price) {
		return price.multiply(new BigDecimal(quanrity));

	}

//	calculate the average 
	private BigDecimal priceAverage(BigDecimal lastValue, BigDecimal currentValue) {
		if (lastValue == null || currentValue == null) {
			throw new MsgException("Não é possível calcular a média. Alguns dos valores são nulos.");
		}

		BigDecimal sum = lastValue.add(currentValue);

		return sum.divide(new BigDecimal("2"));

	}

	private Double quantityAverage(Double lastValue, Double currentValue) {
		if (lastValue == null || currentValue == null) {
			throw new MsgException("Não é possível calcular a média. Alguns dos valores são nulos.");
		}

		return Double.valueOf((lastValue + currentValue) / 2);

	}

	private PurchaseItem update(Long id, UpdateItemPurchaseVO updateItemPurchase) {
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

	private void delete(Long id) {
		purchaseItemRepository.deleteById(id);
	}
	private Product findOldOrRegisterNewProduct(PurchaseItemVO purchaseItemVO) {
		Product procudt= new Product();
//		try {
//			procudt = procudService.findSpecificProduct(purchaseItemVO.getName(), purchaseItemVO.getBrand());
//		} catch (MsgException e) {
//
//			RegisterProductDTO registerProductDto = new RegisterProductDTO(purchaseItemVO.getName(),
//					purchaseItemVO.getBrand(), purchaseItemVO.getMeasurement_unit(),
//					purchaseItemVO.getQuantity_product(), purchaseItemVO.getPrice());
//
//			procudt = procudService.registration(registerProductDto);
//		}
		return procudt;
	}

}
