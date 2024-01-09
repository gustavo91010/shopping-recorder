package com.ajudaqui.recalldecompras.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ajudaqui.recalldecompras.entity.Purchase;
import com.ajudaqui.recalldecompras.entity.Users;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceTest {

	@InjectMocks
	private PurchaseService purchaseService;

	
	
	private Purchase purchase() {
		Users user = new Users();
		user.setName("compra teste");
		user.setEmail("emailTest@email.com");
		return new Purchase(user);
	}
	
	private  List<Purchase> purchases() {
		List<Purchase> purchases= new ArrayList<Purchase>();
		Users user = new Users();
		user.setName("compra teste 01");
		user.setEmail("email_test_01@email.com");
		
		Users user2 = new Users();
		user.setName("compra teste 02");
		user.setEmail("email_test_02@email.com");
		
		Users user3 = new Users();
		user.setName("compra teste 03");
		user.setEmail("email_test_03@email.com");
		
		purchases.add(new Purchase(user));
		purchases.add(new Purchase(user2));
		purchases.add(new Purchase(user3));
		
		return purchases;

		
	}
}
