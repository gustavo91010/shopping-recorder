package com.ajudaqui.recalldecompras.service;

import static java.lang.String.format;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajudaqui.recalldecompras.config.client.dto.UsersDTO;
import com.ajudaqui.recalldecompras.dto.ItemResumeDTO;
import com.ajudaqui.recalldecompras.dto.RegisterProductDTO;
import com.ajudaqui.recalldecompras.dto.response.ApiResumeItens;
import com.ajudaqui.recalldecompras.entity.Product;
import com.ajudaqui.recalldecompras.entity.Purchase;
import com.ajudaqui.recalldecompras.entity.PurchaseItem;
import com.ajudaqui.recalldecompras.exception.MsgException;
import com.ajudaqui.recalldecompras.repository.PurchaseItensRepository;
import com.ajudaqui.recalldecompras.service.model.PurchaseItemVO;

@Service
public class PurchaseItemService {
	Logger logger = LoggerFactory.getLogger(PurchaseItemService.class);

	@Autowired
	private PurchaseItensRepository purchaseItemRepository;

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private ProductService procudService;

	@Autowired
	private UsersService usersService;

	public Purchase newItem(String jwtToken, PurchaseItemVO purchaseItemVO) {
		Purchase purchase = purchaseService.findByName(purchaseItemVO.getPurchase_name(), jwtToken);
		myPurchase(jwtToken, purchase);

		logger.info(format("Adicionando produto %s a compra id %d", purchaseItemVO.getName(), purchase.getId()));

		Product procudt = findOldOrRegisterNewProduct(purchaseItemVO);
		// Verificar se o produto já existe na lista, incrementa
		for (PurchaseItem item : purchase.getItems()) {
			if (item.getProduct().equals(procudt)) {
				logger.info(format("Incrementando item já existente"));

				Double quantityItem = item.getQuantity();
				quantityItem = quantityItem + purchaseItemVO.getQuantity_items();

				item.setQuantity(quantityItem);
				item.setPrice_total(attTotalPrice(quantityItem, item.getProduct().getPrice()));

				return addItemInPurchase(purchase, item);
			}
		}

		PurchaseItem item = new PurchaseItem(purchase, procudt, purchaseItemVO.getQuantity_items());
		logger.info(format("Adicioando novo item."));

		return addItemInPurchase(purchase, item);
	}

	private Purchase addItemInPurchase(Purchase purchase, PurchaseItem item) {
		item = save(item);
		purchase.getItems().add(item);

		purchaseService.update(purchase);
		return purchase;
	}

	private PurchaseItem findById(Long id) {
		Optional<PurchaseItem> item = purchaseItemRepository.findById(id);
		if (item.isEmpty()) {
			logger.warn("Item não encontrado");

			throw new MsgException("Item não encontrado");
		}
		return item.get();
	}

	public PurchaseItem findByName(String jwt, String purchaseName, String productName) {
		Purchase purchase = purchaseService.findByName(purchaseName,jwt);

		return purchase.getItems().stream()
				.filter(item -> item.getProduct().getName().equals(productName))
				.findFirst()
				.orElseThrow(() -> new MsgException("Item não encontrado"));

	}

	public ApiResumeItens findAll(String jwtToken, String purchaseName) {
		purchaseName = purchaseName.replace(" ", "_");
		Purchase purchases = purchaseService.findByName(purchaseName, jwtToken);
		List<ItemResumeDTO> itensResume = new ArrayList<>();

		for (PurchaseItem purchase : purchases.getItems()) {
			itensResume.add(new ItemResumeDTO(purchase));
		}
		return new ApiResumeItens(purchases.getId(), purchases.getName(), purchases.getTotalValue(), itensResume);
	}

	private BigDecimal attTotalPrice(Double quanrity, BigDecimal price) {
		return price.multiply(new BigDecimal(quanrity));

	}

	public void updateQuantityItems(String jwt, Long itemId, Double quantity) {
		UsersDTO user = usersService.findByJwt(jwt);
		PurchaseItem item = findById(itemId);
		if (!item.getPurchase().getUser_id().equals(user.getId())) {
			logger.warn("Exclusão não autorizada");

			throw new MsgException("Exclusão não autorizada");

		}
		item.setQuantity(quantity);

		if (item.getQuantity() < 0) {
			item.setQuantity(0.0);
		}
		item.setTotalValue(attTotalPrice(item.getQuantity(), item.getProduct().getPrice()));

		save(item);
		Purchase purch = purchaseService.findById(item.getPurchase().getId());
		purch.getItems().removeIf(i -> i.getId().equals(item.getId()));
		purch.getItems().add(item);
		purchaseService.update(purch);

	}

	public void delete(String jwtToken, Long id) {
		UsersDTO user = usersService.findByJwt(jwtToken);
		PurchaseItem item = findById(id);
		if (!item.getPurchase().getUser_id().equals(user.getId())) {
			logger.warn("Exclusão não autorizada");

			throw new MsgException("Exclusão não autorizada");

		}
		purchaseItemRepository.delete(item);

		Purchase purch = purchaseService.findById(item.getPurchase().getId());
		purch.getItems().removeIf(i -> i.getId().equals(item.getId()));
		purchaseService.update(purch);

	}

	private Product findOldOrRegisterNewProduct(PurchaseItemVO purchaseItemVO) {
		Product procudt = new Product();
		List<Product> products = procudService.findSpecificProduct(purchaseItemVO.getName(), purchaseItemVO.getBrand());
		procudt = products.stream().max(Comparator.comparing(Product::getCreated_at)).orElseGet(() -> {
			RegisterProductDTO registerProductDto = new RegisterProductDTO(purchaseItemVO.getName(),
					purchaseItemVO.getBrand(), purchaseItemVO.getMeasurement_unit(),
					purchaseItemVO.getQuantity_product(), purchaseItemVO.getPrice());

			return procudService.registration(registerProductDto);
		});
		return procudt;
	}

	private UsersDTO myPurchase(String jwtToken, Purchase purchase) {
		UsersDTO user = usersService.findByJwt(jwtToken);

		if (!purchase.getUser_id().equals(user.getId())) {
			logger.warn("Exclusão não autorizada");

			throw new MsgException("Não autorizado.");
		}
		return user;
	}

	private PurchaseItem save(PurchaseItem item) {

		return purchaseItemRepository.save(item);

	}

}
