package com.ajudaqui.recalldecompras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajudaqui.recalldecompras.config.client.AuthenticationClient;
import com.ajudaqui.recalldecompras.config.client.dto.LoginRequest;
import com.ajudaqui.recalldecompras.config.client.dto.LoginResponse;
import com.ajudaqui.recalldecompras.config.client.dto.UsersRegister;

@Service
public class AuthenticationService {

	
	@Autowired	
	AuthenticationClient authenticationClient;


	public LoginResponse authenticateUser(LoginRequest loginRequest) {
		return authenticationClient.authenticateUser(loginRequest);
	}

	
	public String registerUser(UsersRegister usersRegister) {
		return authenticationClient.registerUser(usersRegister);
	}
}
