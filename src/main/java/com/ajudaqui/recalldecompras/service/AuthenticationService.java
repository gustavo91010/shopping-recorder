package com.ajudaqui.recalldecompras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajudaqui.recalldecompras.client.AuthenticationClient;
import com.ajudaqui.recalldecompras.client.dto.LoginRequest;
import com.ajudaqui.recalldecompras.client.dto.LoginResponse;
import com.ajudaqui.recalldecompras.client.dto.UsersRegister;

@Service
public class AuthenticationService {

	
	@Autowired	
	AuthenticationClient authenticationClient;


	public LoginResponse authenticateUser(LoginRequest loginRequest) {
		System.out.println("passou no login??");
		return authenticationClient.authenticateUser(loginRequest);
	}

	
	public String registerUser(UsersRegister usersRegister) {
		return authenticationClient.registerUser(usersRegister);
	}
}
