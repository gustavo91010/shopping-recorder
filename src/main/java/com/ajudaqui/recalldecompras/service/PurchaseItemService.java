package com.ajudaqui.recalldecompras.service;

import static java.lang.String.format;

import java.math.BigDecimal;
import java.util.Optional;

import org.apache.tomcat.jni.Proc;
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

		Product procudt = new Product();
		
		//Verifica se o produto ja esta cadastrado, caso não, cadastra ele
		try {
			procudt=	procudService.findSpecificProduct(purchaseItemVO.getName(), purchaseItemVO.getBrand());
		} catch (MsgException e) {
			
			RegisterProductDTO registerProductDto = new RegisterProductDTO();
			registerProductDto.setName(purchaseItemVO.getName());
			registerProductDto.setBrand((purchaseItemVO.getBrand()));
			registerProductDto.setMeasurement_unit(purchaseItemVO.getMeasurement_unit());
			registerProductDto.setPrice(purchaseItemVO.getPrice());
			registerProductDto.setQuantity(purchaseItemVO.getQuantity_product());

			procudt = procudService.registration(registerProductDto);
		}
				
		// Verificar se o produto já existe na lista, incrementa
		System.out.println("chegou aqui");
		for (PurchaseItem item : purchase.getItems()) {
			if (item.getProduct().equals(procudt)) {
				System.out.println("entrada item" + item.toString());
				
				Double quantityItem = item.getQuantity();
				quantityItem =quantityItem+ purchaseItemVO.getQuantity_items();
				item.setQuantity(quantityItem);
				
				item.setPrice_total(attTotalPrice(quantityItem, item.getProduct().getPrice()));

				purchaseItemRepository.save(item);
				System.out.println("saida item" + item.toString());


				return purchase;

			}

		}
		System.out.println("passou dali");

		PurchaseItem item = new PurchaseItem(purchase, procudt, purchaseItemVO.getQuantity_items());

		item = purchaseItemRepository.save(item);
		System.out.println("novo item: " + item.toString());

		purchase.getItems().add(item);

		// Precisa disso para atualizar o purchase mesmo? sera que não da pra por em
		// cascata?

//		new PurchaseItem(purchaseService.attPurchase(purchase), procudt, purchaseItemVO.getQuantity_items());
		return purchase;

	}

	public PurchaseItem findById(Long id) {
		Optional<PurchaseItem> item = purchaseItemRepository.findById(id);
		if (item.isEmpty()) {
			throw new MsgException("Item não encontrado");
		}
		return item.get();

	}
	
	//Atualizando total item
	private BigDecimal attTotalPrice(Double quanrity,BigDecimal price) {
		return price.multiply(new BigDecimal(quanrity));
		
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

	public PurchaseItem update(Long id, UpdateItemPurchaseVO updateItemPurchase) {
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
