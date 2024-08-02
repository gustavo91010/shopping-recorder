package com.ajudaqui.recalldecompras.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajudaqui.recalldecompras.config.client.dto.LoginRequest;
import com.ajudaqui.recalldecompras.config.client.dto.LoginResponse;
import com.ajudaqui.recalldecompras.config.client.dto.UsersRegister;
import com.ajudaqui.recalldecompras.dto.response.MessageResponse;
import com.ajudaqui.recalldecompras.exception.ApiException;
import com.ajudaqui.recalldecompras.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		try {
		LoginResponse userAuthenticated = authenticationService.authenticateUser(loginRequest);

		logger.info("Solocitação de login recebida com sucesso");
		return ResponseEntity.ok(userAuthenticated);

		} catch (Exception e) {
			String msg="Login / senha incorreto";
			logger.warn(msg);
			return new ApiException().response(msg, HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody UsersRegister usersRegister) {

		try {
			authenticationService.registerUser(usersRegister);
			logger.info(String.format("Registro do email %s executado com sucesso!", usersRegister.getEmail()));
			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

		} catch (Exception e) {
			String msg = String.format("Problema ao registrar o email %s, %s", usersRegister.getEmail(),
					e.getMessage());
			logger.warn(msg);
			return new ApiException().response(msg, HttpStatus.UNAUTHORIZED);
		}
	}

}
