package com.ajudaqui.recalldecompras.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajudaqui.recalldecompras.dto.response.ApiPurchase;
import com.ajudaqui.recalldecompras.dto.response.ApiPurchases;
import com.ajudaqui.recalldecompras.entity.Purchase;
import com.ajudaqui.recalldecompras.exception.ApiException;
import com.ajudaqui.recalldecompras.exception.MsgException;
import com.ajudaqui.recalldecompras.exception.NotFoundEntityException;
import com.ajudaqui.recalldecompras.service.PurchaseService;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;

	@PostMapping()
	public ResponseEntity<?> newPurchase(@RequestHeader("name") String name, @RequestHeader("jwt") String jwt) {
		try {
			Purchase product = purchaseService.newPurchase(name, jwt);
			return new ResponseEntity<>(new ApiPurchase(product), HttpStatus.CREATED);

		} catch (MsgException e) {
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		} catch (NotFoundEntityException e) {
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/{name}")
	public ResponseEntity<?> findByName(@PathVariable("name") String name,@RequestHeader("authorization") String jwt) {
		try {
			Purchase product = purchaseService.findByName(name,jwt);
			return new ResponseEntity<>(new ApiPurchase(product), HttpStatus.CREATED);

		} catch (MsgException e) {
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		}

		catch (NotFoundEntityException e) {
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping()
	public ResponseEntity<?> findByAll(@RequestHeader("authorization") String jwt) {
		try {
			 List<Purchase> product = purchaseService.findAllByUsers(jwt);
			 
			return new ResponseEntity<>(new ApiPurchases(product), HttpStatus.CREATED);

		} catch (MsgException e) {
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		}

		catch (NotFoundEntityException e) {
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		}
	}

}
