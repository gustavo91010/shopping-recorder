package com.ajudaqui.recalldecompras.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajudaqui.recalldecompras.dto.UsersDto;
import com.ajudaqui.recalldecompras.entity.Users;
import com.ajudaqui.recalldecompras.exception.NotFoundEntityException;
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
		users.setCreated_at(LocalDateTime.now());
		users.setUpdated_at(LocalDateTime.now());
		
		
		usersRepository.save(users);
		return users;
		
	}
	public Users findById(Long id) {
		Optional<Users> usres = usersRepository.findById(id);
		
		if(!usres.isPresent()) {
			throw new NotFoundEntityException("Usuario não encontrado");
		}
		return usres.get();
		
		
	}
	public Users findByNnme(String name) {
		Optional<Users> usres = usersRepository.findByName(name);
		
		if(!usres.isPresent()) {
			throw new NotFoundEntityException("Usuario não encontrado");
		}
		return usres.get();
		
		
	}
	public List<Users> findAll() {
		return usersRepository.findAll();
	}
	
	public void update(Long id, UsersDto usersDto) {
		Users users = findById(id);
		
		if(!usersDto.getName().isEmpty()) {
			users.setName(usersDto.getName());
		}
		if(!usersDto.getEmail().isEmpty()) {
			users.setEmail(usersDto.getEmail());
		}
		users.setUpdated_at(LocalDateTime.now());
		
	}
	
	public void delete(Long id) {
		usersRepository.deleteById(id);
	}
	

}
