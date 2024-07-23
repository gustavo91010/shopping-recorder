package com.ajudaqui.recalldecompras.controller;

import static java.lang.String.format;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ajudaqui.recalldecompras.dto.RegisterProductDTO;
import com.ajudaqui.recalldecompras.dto.response.ApiProduct;
import com.ajudaqui.recalldecompras.entity.Product;
import com.ajudaqui.recalldecompras.exception.ApiException;
import com.ajudaqui.recalldecompras.service.ProductService;
import com.ajudaqui.recalldecompras.service.model.ProductVO;

@RestController
@RequestMapping("/product")
public class ProductController {
	Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@PostMapping
	@Transactional
	public ResponseEntity<?> register(@RequestBody RegisterProductDTO usersDto) {
		try {
			Product product = productService.registration(usersDto);

			return new ResponseEntity<>(new ApiProduct(product), HttpStatus.CREATED);

		} catch (Exception e) {
			logger.warn(e.getMessage());
			return new ApiException().response(e, HttpStatus.UNAUTHORIZED);
		}

	}

	@Transactional
	@GetMapping("/id/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		logger.info(format("Buscando produto de id %d", id));
		try {
			Product product = productService.findById(id);

			return new ResponseEntity<>(new ApiProduct(product), HttpStatus.CREATED);

		} catch (Exception e) {
			logger.warn(e.getMessage());
			return new ApiException().response(e, HttpStatus.UNAUTHORIZED);
		}

	}

	@Transactional
	@GetMapping("/name/{name}")
	public ResponseEntity<?> findByName(@PathVariable("name") String name) {
		logger.info(format("Buscando produto: %s", name));
		try {
			 List<Product> products = productService.findByName(name);

			return new ResponseEntity<>(new ApiProducts(products), HttpStatus.CREATED);

		} catch (Exception e) {
			logger.warn(e.getMessage());
			return new ApiException().response(e, HttpStatus.UNAUTHORIZED);
		}

	}
	@Transactional
	@GetMapping("/brand/{brand}")
	public ResponseEntity<?> findByBrand(@PathVariable("brand") String brand) {
		logger.info(format("Buscando pela marca: %s", brand));
		try {
			 List<Product> products = productService.findByBrand(brand);

			return new ResponseEntity<>(new ApiProducts(products), HttpStatus.CREATED);

		} catch (Exception e) {
			logger.warn(e.getMessage());
			return new ApiException().response(e, HttpStatus.UNAUTHORIZED);
		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ProductVO productUpdate) {
		logger.info(format("Buscando produto de id %d para atualizae", id));

		try {
			Product product =	productService.update(id, productUpdate);

			return new ResponseEntity<>(new ApiProduct(product), HttpStatus.CREATED);

		} catch (Exception e) {
			logger.warn(e.getMessage());
			return new ApiException().response(e, HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping("/change-price/{id}")
	public void changePrice(@PathVariable("id") Long id, @RequestParam("price") double price) {
		productService.changePrice(id, price);
	}

}
