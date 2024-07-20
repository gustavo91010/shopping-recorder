package com.ajudaqui.recalldecompras.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ajudaqui.recalldecompras.dto.UsersDTO;

@FeignClient(name = "authentication-client", url = "${url.authentication}")

public interface AuthenticationClient {

	
	@GetMapping("/users/jwt")
	public UsersDTO findByJwt(@RequestHeader("Authorization") String jwtToken);
}
