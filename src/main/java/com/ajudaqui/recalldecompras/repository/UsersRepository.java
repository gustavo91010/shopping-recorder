package com.ajudaqui.recalldecompras.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajudaqui.recalldecompras.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{

	Optional<Users> findByName(String name);

}
