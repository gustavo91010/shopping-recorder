package com.ajudaqui.recalldecompras.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajudaqui.recalldecompras.config.client.dto.UsersDTO;
import com.ajudaqui.recalldecompras.entity.Purchase;
import com.ajudaqui.recalldecompras.entity.PurchaseItem;
import com.ajudaqui.recalldecompras.exception.MsgException;
import com.ajudaqui.recalldecompras.exception.NotFoundEntityException;
import com.ajudaqui.recalldecompras.repository.PurchaseRepository;

@Service
public class PurchaseService {
	Logger logger = LoggerFactory.getLogger(PurchaseService.class);

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private UsersService usersService;

	public Purchase newPurchase(String name, String jwt) {
		UsersDTO user = usersService.findByJwt(jwt);
		if (user == null) {
			logger.warn("Usuário não encontrado");

			throw new MsgException("Usuário não encontrado");
		}
		if (!purchaseRepository.findByName(name.replace(" ", "_")).isEmpty()) {
			String msg = "Este nome já esta em uso.";
			logger.warn(msg);
			throw new MsgException(msg);
		}
		Purchase purchase = new Purchase(name.toLowerCase(), user.getId());

		purchase = save(purchase);
		logger.info("Iniciando nova compra.");

		return purchase;
	}

//	shopping_recorder
	// clonagem do historico de compras
	private Purchase shoppingClone(Long userId, Long purchaseId) {
		if (!purchasePertenceUser(userId, purchaseId)) {
			logger.warn("Compra não localizada.");

			throw new NotFoundEntityException("Compra não localizada.");
		}
		Purchase purchase = findById(purchaseId);

//		ele adiciona os itens da utima lista nessa lista
		purchase.getItems().forEach(item -> item.getProduct().setPrice(BigDecimal.ZERO));
		return purchase;
	}

	public Purchase findById(Long id) {
		Purchase purchase = purchaseRepository.findById(id)
				.orElseThrow(() -> new NotFoundEntityException("Compra não localizada."));
		return purchase;
	}

	private boolean purchasePertenceUser(Long userId, Long purchaseId) {
		Purchase purchase = findById(purchaseId);
		return purchase.getUser_id() == userId;
	}

	public List<Purchase> findAllByUsers(String jwt) {
		UsersDTO user = usersService.findByJwt(jwt);
		logger.warn("Todas as compras do user: " + user.getId());

		return purchaseRepository.findAllByUsers(user.getId());
	}

	private BigDecimal totalPrice(Purchase purchase) {

		BigDecimal total = BigDecimal.ZERO;
		for (PurchaseItem item : purchase.getItems()) {
			total = total.add(item.getTotalValue());
		}
		return total;
	}

	public Purchase findByName(String name, String jwt) {

		Optional<Purchase> purchase = purchaseRepository.findByName(name.replace(" ", "_"));
		if (purchase.isEmpty()) {
			String msg = "Compra não encontrada";
			logger.warn(msg);

			throw new NotFoundEntityException(msg);
		}
		UsersDTO user = usersService.findByJwt(jwt);
		logger.info(String.format("verificando responsavel pela compra: %s .", name));


		if (user.getId() != purchase.get().getUser_id()) {
			String msg = "Não autorizado";
			logger.warn(msg);

			throw new MsgException(msg);
		}
		return purchase.get();
	}

	private Purchase save(Purchase purchase) {
		purchase.setName(purchase.getName().replace(" ", "_"));
		purchase.setUpdated_at(LocalDateTime.now());

		return purchaseRepository.save(purchase);
		
	}
	public Purchase update(Purchase purchase) {
		purchase.setTotalValue(totalPrice(purchase));;

		return save(purchase);
		
	}

}
