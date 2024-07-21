package com.ajudaqui.recalldecompras.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ajudaqui.recalldecompras.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>{

	@Query(value="select * from purchases where users_id= :userId ", nativeQuery = true)
	List<Purchase> findAllByUsers(Long userId);
	
	Optional<Purchase> findByName(String name);

	
	


}
