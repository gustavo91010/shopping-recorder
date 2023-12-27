package com.ajudaqui.recalldecompras.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajudaqui.recalldecompras.dto.UsersDto;
import com.ajudaqui.recalldecompras.entity.Users;
import com.ajudaqui.recalldecompras.repository.UsersRepository;

@Service
public class UsersService {
	
	@Autowired
	UsersRepository usersRepository;
	
	public Users register(UsersDto usersDto) {
//		Users users= usersDto.toUsers();
		Users users= new Users();
		users.setName(usersDto.getName());
		users.setEmail(usersDto.getEmail());
		users.setPassword(usersDto.getPassword());
		
		
		System.err.println(users.toString());
		usersRepository.save(users);
		return users;
		
	}
	public List<Users> findAll() {
		return usersRepository.findAll();
	}

}
