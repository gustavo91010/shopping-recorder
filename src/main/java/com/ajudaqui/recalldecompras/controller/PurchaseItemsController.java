package com.ajudaqui.recalldecompras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajudaqui.recalldecompras.dto.response.ApiPurchase;
import com.ajudaqui.recalldecompras.dto.response.ApiPurchaseItem;
import com.ajudaqui.recalldecompras.entity.Purchase;
import com.ajudaqui.recalldecompras.exception.ApiException;
import com.ajudaqui.recalldecompras.exception.MsgException;
import com.ajudaqui.recalldecompras.exception.NotFoundEntityException;
import com.ajudaqui.recalldecompras.service.PurchaseItemService;
import com.ajudaqui.recalldecompras.service.model.PurchaseItemVO;

@RestController
@RequestMapping("/purchase-items")
public class PurchaseItemsController {

	@Autowired
	private PurchaseItemService purchaseItemService;

	@PostMapping()
	public ResponseEntity<?> newItem(@RequestHeader("authorization") String jwtToken,
			@RequestBody PurchaseItemVO purchaseItemVO) {
		try {

			Purchase response = purchaseItemService.newItem(jwtToken, purchaseItemVO);
			return new ResponseEntity<>(new ApiPurchase(response), HttpStatus.CREATED);
		} catch (MsgException e) {
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		} catch (NotFoundEntityException e) {
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/all/{purchase-name}")
	public ResponseEntity<?> findAll(@RequestHeader("authorization") String jwtToken, @PathVariable("purchase-name") String purchaseName) {
		try {
// todos os litens da lista
			ApiPurchaseItem response = purchaseItemService.findAll(jwtToken, purchaseName);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (MsgException e) {
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		} catch (NotFoundEntityException e) {
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		}

	}
}
