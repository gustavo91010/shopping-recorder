package com.ajudaqui.recalldecompras.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajudaqui.recalldecompras.entity.Product;
import com.ajudaqui.recalldecompras.entity.PurchaseItem;

public interface ProductRepository extends JpaRepository<Product, Long>{

List<Product> findByName(String name);
	
	List<Product> findByBrand(String brand);
}
