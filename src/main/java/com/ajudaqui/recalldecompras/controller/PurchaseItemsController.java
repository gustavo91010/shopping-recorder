package com.ajudaqui.recalldecompras.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ajudaqui.recalldecompras.dto.response.ApiMessage;
import com.ajudaqui.recalldecompras.dto.response.ApiPurchaseItem;
import com.ajudaqui.recalldecompras.dto.response.ApiResumeItens;
import com.ajudaqui.recalldecompras.dto.response.ApiResumeItens;
import com.ajudaqui.recalldecompras.entity.PurchaseItem;
import com.ajudaqui.recalldecompras.exception.ApiException;
import com.ajudaqui.recalldecompras.exception.MsgException;
import com.ajudaqui.recalldecompras.exception.NotFoundEntityException;
import com.ajudaqui.recalldecompras.service.PurchaseItemService;
import com.ajudaqui.recalldecompras.service.model.PurchaseItemVO;

@RestController
@RequestMapping("/purchase-items")
public class PurchaseItemsController {
	Logger logger = LoggerFactory.getLogger(PurchaseItemsController.class);

	@Autowired
	private PurchaseItemService purchaseItemService;

	@PostMapping()
	public ResponseEntity<?> newItem(@RequestHeader("authorization") String jwtToken,
			@RequestBody PurchaseItemVO purchaseItemVO) {
		try {
			String msg = "Item adicionado com sucesso!";
			purchaseItemService.newItem(jwtToken, purchaseItemVO);
			logger.info(msg);
			return new ResponseEntity<>(new ApiMessage(msg), HttpStatus.CREATED);
		} catch (MsgException e) {
			logger.warn(e.getMessage());
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		} catch (NotFoundEntityException e) {
			logger.warn(e.getMessage());

			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.warn(e.getMessage());

			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		}

	}
	@GetMapping("/find-by-name")
	public ResponseEntity<?> findByName(
			@RequestHeader("authorization") String jwtToken,
			@RequestParam("purchase-name") String purchaseName ,
			@RequestParam("product-name") String productName) 
	{
		System.out.println("purchaseName "+purchaseName);
		try {
			
			 PurchaseItem response = purchaseItemService.findByName(jwtToken, purchaseName,productName);
			return new ResponseEntity<>(new ApiPurchaseItem(response), HttpStatus.CREATED);
		} catch (MsgException e) {
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		} catch (NotFoundEntityException e) {
			logger.warn(e.getMessage());
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.warn(e.getMessage());
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/all/{purchase-name}")
	public ResponseEntity<?> findAll(@RequestHeader("authorization") String jwtToken,
			@PathVariable("purchase-name") String purchaseName) {
		try {
			ApiResumeItens response = purchaseItemService.findAll(jwtToken, purchaseName);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (MsgException e) {
			logger.warn(e.getMessage());
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		} catch (NotFoundEntityException e) {
			logger.warn(e.getMessage());
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.warn(e.getMessage());
			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/{id}/{quantity}")
	public ResponseEntity<?> updateQuantityItems(
			@RequestHeader("authorization") String jwtToken,
			@PathVariable("id") Long id,
			@PathVariable("quantity") Double quantity) {
		try {
			String msg = "Item quantidade atualizado com sucesso!";
			purchaseItemService.updateQuantityItems(jwtToken, id,quantity);
			logger.info(msg);
			return new ResponseEntity<>(msg, HttpStatus.CREATED);
		} catch (MsgException e) {
			logger.warn(e.getMessage());

			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		} catch (NotFoundEntityException e) {
			logger.warn(e.getMessage());

			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.warn(e.getMessage());

			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@RequestHeader("authorization") String jwtToken, @PathVariable("id") Long id) {
		try {
			String msg = "Item excluido com sucesso!";
			purchaseItemService.delete(jwtToken, id);
			logger.info(msg);
			return new ResponseEntity<>(msg, HttpStatus.CREATED);
		} catch (MsgException e) {
			logger.warn(e.getMessage());

			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		} catch (NotFoundEntityException e) {
			logger.warn(e.getMessage());

			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.warn(e.getMessage());

			return new ApiException().response(e, HttpStatus.BAD_REQUEST);
		}

	}
}
