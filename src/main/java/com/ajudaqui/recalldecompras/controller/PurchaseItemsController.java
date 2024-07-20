package com.ajudaqui.recalldecompras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajudaqui.recalldecompras.dto.response.ApiPurchaseItemService;
import com.ajudaqui.recalldecompras.entity.PurchaseItem;
import com.ajudaqui.recalldecompras.exception.ApiException;
import com.ajudaqui.recalldecompras.service.PurchaseItemService;
import com.ajudaqui.recalldecompras.service.model.PurchaseItemVO;

@RestController
@RequestMapping("/purchase-items")
public class PurchaseItemsController {

	@Autowired
	private PurchaseItemService purchaseItemService;

	@PostMapping()
	public ResponseEntity<?> newItem(@RequestBody PurchaseItemVO purchaseItemVO) {
		try {

			PurchaseItem response = purchaseItemService.newItem(purchaseItemVO);
			return new ResponseEntity<>(new ApiPurchaseItemService(response), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		}

	}
////	public ResponseEntity<?> register(@RequestHeader("name") String name,@RequestHeader("jwt") String jwt) {
//		try {
//			Purchase product = purchaseItemService.newItem(null, null, null)
//			return new ResponseEntity<>(new ApiPurchase(product), HttpStatus.CREATED);
//
//		} catch (MsgException e) {
//			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
//		}
//
}
