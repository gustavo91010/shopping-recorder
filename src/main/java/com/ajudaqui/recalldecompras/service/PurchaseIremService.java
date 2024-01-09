package com.ajudaqui.recalldecompras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajudaqui.recalldecompras.dto.RegisterProductDto;
import com.ajudaqui.recalldecompras.entity.Product;
import com.ajudaqui.recalldecompras.entity.Purchase;
import com.ajudaqui.recalldecompras.entity.PurchaseItem;
import com.ajudaqui.recalldecompras.repository.PurchaseItensRepository;

@Service
public class PurchaseIremService {

	@Autowired
	private PurchaseItensRepository purchaseItemRepository;
//	@Autowired
//	private PurchaseRepository purchaseRepository;
	
	

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private ProductService procudService;

	public PurchaseItem newItem(Long purchaseId,RegisterProductDto registerProductDto,int quant) {
	
		Purchase purchase = purchaseService.findById(purchaseId);
		Product procudt = procudService.registration(registerProductDto);

		PurchaseItem item = new PurchaseItem(purchase, procudt, quant);
		item = purchaseItemRepository.save(item);

		purchase.getItems().add(item);
		
		// Precisa disso para atualizar o purchase mesmo? sera que não da pra por em cascata?
		
		return new PurchaseItem(purchaseService.attPurchase(purchase), procudt, quant);

	}
	public PurchaseItem addItem(Long purchaseId, Long productId,int quant) {
		
		Purchase purchase = purchaseService.findById(purchaseId);
		Product procudt = procudService.findById(productId);

		PurchaseItem item = new PurchaseItem(purchase, procudt, quant);
		item = purchaseItemRepository.save(item);

		purchase.getItems().add(item);
		
		// Precisa disso para atualizar o purchase mesmo? sera que não da pra por em cascata?
		
		return new PurchaseItem(purchaseService.attPurchase(purchase), procudt, quant);

	}
	
	// atualizar item

}
