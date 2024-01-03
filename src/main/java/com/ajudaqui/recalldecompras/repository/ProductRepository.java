package com.ajudaqui.recalldecompras.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajudaqui.recalldecompras.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
//	Optional<Users> findByName(String name);

	List<Product> findByName(String name);
	
	List<Product> findByBrand(String brand);


}
