package com.ajudaqui.recalldecompras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajudaqui.recalldecompras.entity.PurchaseItem;

public interface PurchaseItensRepository extends JpaRepository<PurchaseItem, Long>{
//	Optional<Users> findByName(String name);

	


}
