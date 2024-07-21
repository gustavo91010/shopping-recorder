package com.ajudaqui.recalldecompras.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ajudaqui.recalldecompras.client.dto.UsersDTO;
import com.ajudaqui.recalldecompras.entity.Purchase;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceTest {

	@InjectMocks
	private PurchaseService purchaseService;

	
	
	private Purchase purchase() {
		UsersDTO user = new UsersDTO();
		user.setName("compra teste");
		user.setEmail("emailTest@email.com");
		return new Purchase("primeira compra",1L);
	}
	
	private  List<Purchase> purchases() {
		List<Purchase> purchases= new ArrayList<Purchase>();
		UsersDTO user = new UsersDTO();
		user.setName("compra teste 01");
		user.setEmail("email_test_01@email.com");
		
		UsersDTO user2 = new UsersDTO();
		user.setName("compra teste 02");
		user.setEmail("email_test_02@email.com");
		
		UsersDTO user3 = new UsersDTO();
		user.setName("compra teste 03");
		user.setEmail("email_test_03@email.com");
		
//		purchases.add(new Purchase(user));
//		purchases.add(new Purchase(user2));
//		purchases.add(new Purchase(user3));
		
		return purchases;

		
	}
}
