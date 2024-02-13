package com.ajudaqui.recalldecompras.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajudaqui.recalldecompras.dto.RegisterProductDto;
import com.ajudaqui.recalldecompras.entity.Product;
import com.ajudaqui.recalldecompras.exception.MsgException;
import com.ajudaqui.recalldecompras.exception.NotFoundEntityException;
import com.ajudaqui.recalldecompras.repository.ProductRepository;
import com.ajudaqui.recalldecompras.service.model.ProductVo;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product registration(RegisterProductDto registerProductDto) {

		if (productIsRegisteder(registerProductDto.getName(), registerProductDto.getBrand())) {
			throw new MsgException("produto já cadastrado");
		}

		Product product = new Product();
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

		if (!product.isPresent()) {
			throw new NotFoundEntityException("Produto não encontrado");
		}
		return product.get();
	}

	public List<Product> findByName(String name) {
		List<Product> products = productRepository.findByName(name);

		if (products.isEmpty()) {
			throw new MsgException("Nenhum produto com esse nome foi encontrado");
		}
		return products;

	}

	public List<Product> findByBrand(String brand) {
		List<Product> products = productRepository.findByBrand(brand);

		if (products.isEmpty()) {
			throw new MsgException("Nenhum produto com essa marca foi encontrado");
		}
		return products;

	}

	public boolean productIsRegisteder(String name, String brand) {
		List<Product> sameName = productRepository.findByName(name);

		if (sameName.size() > 0) {
			for (Product product : sameName) {

				if (product.getBrand().equalsIgnoreCase(brand)) {
					return true;
				}
			}

		}

		return false;

	}

	public Product update(Long id, ProductVo productUpdate) {
		Product product = findById(id);

		if (!productUpdate.getName().isEmpty()) {
			product.setName(productUpdate.getName());
		}
		if (!productUpdate.getBrand().isEmpty()) {
			product.setBrand(productUpdate.getBrand());
		}
		if (productUpdate.getPrice() != null) {
			product.setPrice(productUpdate.getPrice());
		}
		if (!productUpdate.getAverage_unit().isEmpty()) {
			product.setAverage_unit(productUpdate.getAverage_unit());
		}
		product.setCreated_at(LocalDateTime.now());
		product.setUpdated_at(LocalDateTime.now());

		return product;

	}

	public Product changePrice(Long id, double newPrice) {

		Product product = findById(id);
		product.setPrice(new BigDecimal(newPrice));

		productRepository.save(product);
		return product;

	}

	public void delete(Long id) {
		productRepository.deleteById(id);
	}

}
