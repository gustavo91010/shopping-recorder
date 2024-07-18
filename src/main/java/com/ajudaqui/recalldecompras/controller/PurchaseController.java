package com.ajudaqui.recalldecompras.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajudaqui.recalldecompras.dto.response.ApiPurchase;
import com.ajudaqui.recalldecompras.entity.Purchase;
import com.ajudaqui.recalldecompras.exception.MsgException;
import com.ajudaqui.recalldecompras.service.PurchaseService;

@RestController
@RequestMapping("/compras")
public class PurchaseController {
	
	@Autowired
	private PurchaseService purchaseService;
	
	@PostMapping
	@Transactional
	@PreAuthorize("dasRole('USER')")
	public ResponseEntity<?> register(@RequestHeader("Authorization") String jwt) {
		try {
			 Purchase product = purchaseService.newPurchase(jwt);
			return new ResponseEntity<>(new ApiPurchase(product), HttpStatus.CREATED);

		} catch (MsgException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping
	public String teste() {
		System.err.println("Chamou!");
		return "Apareceu??";
	}
	@GetMapping("/teste")
	public String teste2() {
		System.err.println("Chamou o teste?");
		return "Apareceu algo ai??";
	}

}
