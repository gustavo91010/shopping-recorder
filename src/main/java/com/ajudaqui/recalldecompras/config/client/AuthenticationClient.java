package com.ajudaqui.recalldecompras.config.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ajudaqui.recalldecompras.config.client.dto.LoginRequest;
import com.ajudaqui.recalldecompras.config.client.dto.LoginResponse;
import com.ajudaqui.recalldecompras.config.client.dto.UsersDTO;
import com.ajudaqui.recalldecompras.config.client.dto.UsersRegister;

@FeignClient(name = "authentication-client", url = "${url.authentication}")
public interface AuthenticationClient {

	
	@GetMapping("/users/jwt")
	public UsersDTO findByJwt(@RequestHeader("Authorization") String jwt);
	
	
	@PostMapping("/auth/signin")
    public LoginResponse authenticateUser( @RequestBody LoginRequest loginRequest);
	
	@PostMapping("/auth/signup")
	public String registerUser(@RequestBody UsersRegister usersRegister);
}
