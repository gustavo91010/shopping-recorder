package com.ajudaqui.recalldecompras.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajudaqui.recalldecompras.entity.Product;
import com.ajudaqui.recalldecompras.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>{


}
