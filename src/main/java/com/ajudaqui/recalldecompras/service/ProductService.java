package com.ajudaqui.recalldecompras.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ajudaqui.recalldecompras.dto.RegisterProductDto;
import com.ajudaqui.recalldecompras.entity.Product;
import com.ajudaqui.recalldecompras.exception.MsgException;
import com.ajudaqui.recalldecompras.exception.NotFoundEntityException;
import com.ajudaqui.recalldecompras.repository.ProductRepository;


public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public Product registration(RegisterProductDto registerProductDto) {
		Product product= new Product();
		product.setName(registerProductDto.getName());
		product.setBrand(registerProductDto.getBrand());
		product.setAverage_unit(registerProductDto.getAverage_unit());
		product.setPrice(registerProductDto.getPrice());
		product.setCreated_at(LocalDateTime.now());
		product.setUpdated_at(LocalDateTime.now());

		return productRepository.save(product);
		
	}
	
	public Product findById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		
		if(!product.isPresent()) {
			throw new NotFoundEntityException("Produto não encontrado");
		}
		return product.get();
	}
	
	public List<Product> findByName(String name) {
		List<Product> products = productRepository.findByName(name);
		
		if(products.isEmpty()) {
			throw new  MsgException("Nenhum produto com esse nome foi encontrado");
		}
		return products;
		
	}
	public List<Product> findByBrand(String brand) {
		List<Product> products = productRepository.findByBrand(brand);
		
		if(products.isEmpty()) {
			throw new  MsgException("Nenhum produto com essa marca foi encontrado");
		}
		return products;
		
	}

	
	

}
