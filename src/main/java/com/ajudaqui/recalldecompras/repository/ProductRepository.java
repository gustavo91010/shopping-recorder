package com.ajudaqui.recalldecompras.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ajudaqui.recalldecompras.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query(value = "SELECT * FROM products where name = :name ", nativeQuery = true)
	List<Product> findByName(String name);
	
	@Query(value = "SELECT * FROM products where brand = :brand ", nativeQuery = true)
	List<Product> findByBrand(String brand);

	@Query(value = "SELECT * FROM products WHERE name = :name AND brand = :brand ", nativeQuery = true)
//	Optional<Product> findSpecificProduct(String name, String brand);
	List<Product> findSpecificProduct(String name, String brand);
}
