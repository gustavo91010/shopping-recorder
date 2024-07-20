package com.ajudaqui.recalldecompras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajudaqui.recalldecompras.dto.response.ApiPurchase;
import com.ajudaqui.recalldecompras.entity.Purchase;
import com.ajudaqui.recalldecompras.exception.ApiException;
import com.ajudaqui.recalldecompras.exception.MsgException;
import com.ajudaqui.recalldecompras.service.PurchaseService;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;

	@PostMapping()
	public ResponseEntity<?> register(@RequestHeader("name") String name,@RequestHeader("jwt") String jwt) {
		try {
			Purchase product = purchaseService.newPurchase(name,jwt);
			return new ResponseEntity<>(new ApiPurchase(product), HttpStatus.CREATED);

		} catch (MsgException e) {
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		}

	}

}