package com.ajudaqui.recalldecompras.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajudaqui.recalldecompras.client.AuthenticationClient;
import com.ajudaqui.recalldecompras.client.dto.UsersDTO;

import feign.FeignException;

@Service
public class UsersService {
	Logger logger= LoggerFactory.getLogger(UsersService.class);
	
	@Autowired	
	AuthenticationClient authenticationClient;
	
	public UsersDTO findByJwt(String jwt) {
		logger.info("Buscando usuario pelo jwt hum...");
		try {
			
			return authenticationClient.findByJwt(jwt);
		} catch (FeignException e) {
//			e.getMessage();
			logger.error(e.getMessage());
		}
		return null;
	}
	

}
