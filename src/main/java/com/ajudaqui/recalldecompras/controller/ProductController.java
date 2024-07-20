package com.ajudaqui.recalldecompras.controller;

import java.util.List;

import javax.transaction.Transactional;

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
import com.ajudaqui.recalldecompras.exception.MsgException;
import com.ajudaqui.recalldecompras.service.ProductService;
import com.ajudaqui.recalldecompras.service.model.ProductVO;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping
	@Transactional
	public ResponseEntity<?> register(@RequestBody RegisterProductDTO usersDto) {
		try {
			Product product = productService.registration(usersDto);
			return new ResponseEntity<>(new ApiProduct(product), HttpStatus.CREATED);

		} catch (MsgException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@Transactional
	@GetMapping("/id/{id}")
	public Product findById(@PathVariable("id") Long id) {
		return productService.findById(id);

	}

	@Transactional
	@GetMapping("/name/{name}")
	public List<Product> findByName(@PathVariable("name") String name) {
		return productService.findByName(name);

	}

	@Transactional
	@GetMapping("/brand/{brand}")
	public List<Product> findByBrand(@PathVariable("brand") String brand) {
		return productService.findByBrand(brand);

	}

	@PutMapping("/update/{id}")
	public void update(@PathVariable("id") Long id, @RequestBody ProductVO productUpdate) {
		productService.update(id, productUpdate);
	}

	@PutMapping("/change-price/{id}")
	public void changePrice(@PathVariable("id") Long id, @RequestParam("price") double price) {
		productService.changePrice(id, price);
	}

	@Transactional
	@GetMapping("/test/{name}")
	public String test(@PathVariable("name") String name) {
		return "ola teste: " + name;

	}

}
