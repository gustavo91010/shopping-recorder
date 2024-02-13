package com.ajudaqui.recalldecompras.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajudaqui.recalldecompras.entity.Purchase;
import com.ajudaqui.recalldecompras.entity.PurchaseItem;
import com.ajudaqui.recalldecompras.entity.Users;
import com.ajudaqui.recalldecompras.exception.NotFoundEntityException;
import com.ajudaqui.recalldecompras.repository.PurchaseRepository;

@Service
public class PurchaseService {

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private UsersService usersService;

	public List<Purchase> newPurchase(Long userId) {
		Users user = usersService.findById(userId);
		Purchase purchase = new Purchase(user);
		purchase = purchaseRepository.save(purchase);

		List<Purchase> purchases = findAllByUsers(userId);

		purchases.add(purchase);

		return purchases;

	}

	// clonagem do historico de compras
	public Purchase shoppingClone(Long userId, Long purchaseId) {
		if (!purchasePertenceUser(userId, purchaseId)) {
			throw new NotFoundEntityException("Compra não localizada.");
		}
		Purchase purchase = findById(purchaseId);

//		ele adiciona os itens da utima lista nessa lista
		purchase.getItems().forEach(item -> item.getProduct().setPrice(BigDecimal.ZERO));
		return purchase;
	}

	public Purchase findById(Long id) {
		Optional<Purchase> purchase = purchaseRepository.findById(id);
		if (purchase.isEmpty()) {
			throw new NotFoundEntityException("Compra não localizada.");
		}

		return purchase.get();
	}

	public boolean purchasePertenceUser(Long userId, Long purchaseId) {
		Purchase purchase = findById(purchaseId);
		return purchase.getUsers().getId() == userId;
	}

	private List<Purchase> findAllByUsers(Long userId) {
		return purchaseRepository.findAllByUsers(userId);

	}


	public Purchase attPurchase(Purchase purchase) {
		purchase = purchaseRepository.save(purchase);
		return purchase;

	}

	public BigDecimal totalPrice(Long id) {
		Purchase purchase = findById(id);

		BigDecimal total = BigDecimal.ZERO;

		for (PurchaseItem item : purchase.getItems()) {
			total = total.add(item.getTotalValue());
		}
		return total;
	}

}
