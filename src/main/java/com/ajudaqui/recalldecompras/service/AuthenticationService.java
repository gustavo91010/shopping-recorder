package com.ajudaqui.recalldecompras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajudaqui.recalldecompras.client.AuthenticationClient;
import com.ajudaqui.recalldecompras.entity.Users;

@Service
public class AuthenticationService {
	
@Autowired	
AuthenticationClient authenticationClient;

public Users findByJwt(String jwtToken) {
	
	return  authenticationClient.findByJwt(jwtToken);
}
	
	
}
