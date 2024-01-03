package com.ajudaqui.recalldecompras.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajudaqui.recalldecompras.dto.RegisterUsersDto;
import com.ajudaqui.recalldecompras.entity.Users;
import com.ajudaqui.recalldecompras.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@PostMapping
	@Transactional
	public Users register(@RequestBody RegisterUsersDto usersDto) {
		return usersService.register(usersDto);
	}
	
	@GetMapping
	public List<Users> findAll() {
	return	usersService.findAll();
	}

}
