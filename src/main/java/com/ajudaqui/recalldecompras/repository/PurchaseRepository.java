package com.ajudaqui.recalldecompras.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ajudaqui.recalldecompras.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>{

	@Query(value="select * from purchases where user_id= :user_id ", nativeQuery = true)
	List<Purchase> findAllByUsers(Long user_id);
	
//	@Query(value="select * from purchases where name= :name ", nativeQuery = true)
	Optional<Purchase> findByName(String name);

	
	


}
