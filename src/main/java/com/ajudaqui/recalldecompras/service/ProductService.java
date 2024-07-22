package com.ajudaqui.recalldecompras.service;

import static java.lang.String.format;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajudaqui.recalldecompras.dto.RegisterProductDTO;
import com.ajudaqui.recalldecompras.entity.Product;
import com.ajudaqui.recalldecompras.exception.MsgException;
import com.ajudaqui.recalldecompras.exception.NotFoundEntityException;
import com.ajudaqui.recalldecompras.repository.ProductRepository;
import com.ajudaqui.recalldecompras.service.model.ProductVO;
import com.ajudaqui.recalldecompras.utils.Validation;

@Service
public class ProductService {
	Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository productRepository;

	public Product registration(RegisterProductDTO registerProductDto) {

		Validation.isPresencialValues(registerProductDto);

		if (productIsRegisteder(registerProductDto.getName(), registerProductDto.getBrand())) {
			String msg = format("Produto %s da marca %s já cadastrado.", registerProductDto.getName(),
					registerProductDto.getBrand());
			throw new MsgException(msg);
		}
		logger.info(format("Registrando produto %s da marca %s.", registerProductDto.getName(),
				registerProductDto.getBrand()));

		Product product = new Product();
		product.setName(registerProductDto.getName());
		product.setBrand(registerProductDto.getBrand());
		product.setMeasurement_unit(registerProductDto.getMeasurement_unit());
		product.setQuantity(registerProductDto.getQuantity());

		product.setPrice(registerProductDto.getPrice());
		product.setCreated_at(LocalDateTime.now());
		product.setUpdated_at(LocalDateTime.now());

		return productRepository.save(product);

	}

	public Product findById(Long id) {
		logger.info(format("Buscando produto de id %d", id));

		Optional<Product> product = productRepository.findById(id);

		if (!product.isPresent()) {
			String msg = "Produto não encontrado";
			logger.warn(msg);
			throw new NotFoundEntityException(msg);
		}
		return product.get();
	}

	public List<Product> findByName(String name) {
		logger.info(format("Buscando produto de nome %s", name));

		List<Product> products = productRepository.findByName(name);

		if (products.isEmpty()) {
			String msg = "Nenhum produto com esse nome foi encontrado";
			logger.warn(msg);
			throw new MsgException(msg);
		}
		return products;

	}

	public Product findSpecificProduct(String name, String brand) {
		logger.info(format("Buscando produto %s da marca %s", name, brand));

		Optional<Product> product = productRepository.findSpecificProduct(name, brand);
		if (product.isEmpty()) {
			String msg = "Nenhum produto com esse nome foi encontrado";
			logger.warn(msg);
			throw new MsgException(msg);
		}

		return product.get();
	}

	public List<Product> findByBrand(String brand) {
		logger.info(format("Buscando produtos da marca %s", brand));

		List<Product> products = productRepository.findByBrand(brand);

		if (products.isEmpty()) {
			String msg = "Nenhum produto com essa marca foi encontrado";
			logger.warn(msg);
			throw new MsgException(msg);
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

	public Product update(Long id, ProductVO productUpdate) {
		Product product = findById(id);
		logger.info(format("Atualizando produto %s da marca %s.", product.getName(), product.getBrand()));

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
			product.setMeasurement_unit(productUpdate.getAverage_unit());
		}
		product.setCreated_at(LocalDateTime.now());
		product.setUpdated_at(LocalDateTime.now());

		return product;

	}

	public Product changePrice(Long id, double newPrice) {

		Product product = findById(id);
		logger.info(format("Atualizando o preço produto %s da marca %s.", product.getName(), product.getBrand()));
		product.setPrice(new BigDecimal(newPrice));

		productRepository.save(product);
		return product;

	}

	public void delete(Long id) {
		logger.info(format("Excluindo produto id %d", id));

		productRepository.deleteById(id);
	}

}
