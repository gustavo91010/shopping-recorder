package com.ajudaqui.recalldecompras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajudaqui.recalldecompras.client.AuthenticationClient;
import com.ajudaqui.recalldecompras.dto.UsersDTO;

@Service
public class AuthenticationService {
	
@Autowired	
AuthenticationClient authenticationClient;

public UsersDTO findByJwt(String jwtToken) {
	
	return  authenticationClient.findByJwt(jwtToken);
}
	
	
}
