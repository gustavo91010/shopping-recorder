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
import com.ajudaqui.recalldecompras.utils.enuns.EMeasurementUnit;

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
		product.setName(registerProductDto.getName().toLowerCase());
		product.setBrand(registerProductDto.getBrand().toLowerCase());
		product.setMeasurement_unit(EMeasurementUnit.findByName(registerProductDto.getMeasurement_unit()));
		product.setQuantity(registerProductDto.getQuantity());

		product.setPrice(registerProductDto.getPrice());
		product.setCreated_at(LocalDateTime.now());

		return save(product);

	}

	public List<Product> findAll(String name, String brand) {
		boolean isName = !name.isEmpty() && brand.isEmpty();
		boolean isBrand = name.isEmpty() && !brand.isEmpty();
		boolean isEspecifique = !name.isEmpty() && !brand.isEmpty();
		if (isName) {
			return findByName(name);
		}
		if (isBrand) {
			return findByBrand(brand);
		}
		if (isEspecifique) {
			return findSpecificProduct(name, brand);
		}

		return productRepository.findAll();
	}

	public Product findById(Long id) {

		return productRepository.findById(id).orElseThrow(() -> new NotFoundEntityException("Produto não encontrado"));
	}

	public List<Product> findByName(String name) {
		List<Product> products = productRepository.findByName(name.toLowerCase());

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

	public List<Product> findSpecificProduct(String name, String brand) {
		List<Product> product = productRepository.findSpecificProduct(name.toLowerCase(), brand.toLowerCase());
		return product;
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

		Validation.update(product, productUpdate);
		save(product);

		return product;

	}

	public Product changePrice(Long id, double newPrice) {

		Product product = findById(id);
		logger.info(format("Atualizando o preço produto %s da marca %s.", product.getName(), product.getBrand()));
		product.setPrice(new BigDecimal(newPrice));

		save(product);
		return product;
	}

	public void delete(Long id) {
		productRepository.delete(findById(id));
	}

	private Product save(Product product) {
		logger.info(format("Produto atualizado / registrado com sucesso!"));
		product.setUpdated_at(LocalDateTime.now());
		return productRepository.save(product);
	}

}
