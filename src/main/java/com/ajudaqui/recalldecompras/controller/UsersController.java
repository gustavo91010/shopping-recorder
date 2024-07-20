package com.ajudaqui.recalldecompras.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajudaqui.recalldecompras.dto.RegisterUsersDTO;
import com.ajudaqui.recalldecompras.entity.Users;
import com.ajudaqui.recalldecompras.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@PostMapping
	@Transactional
	public String register(@RequestBody RegisterUsersDTO usersDto) {
		try {
			Users user = usersService.register(usersDto);
			return "Usuário registrado com sucesso! id: "+user.getId();
			
		} catch (UnexpectedRollbackException e) {
			// TODO: handle exception
			return  e.getMessage();
			
		}
	}
	
	@GetMapping
	public List<Users> findAll() {
	return	usersService.findAll();
	}

}
