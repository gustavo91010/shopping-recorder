package com.ajudaqui.recalldecompras.dto;

import com.ajudaqui.recalldecompras.entity.Users;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterUsersDto {
	@JsonProperty("name")
	private String name;
	@JsonProperty("email")
	private String email;

	public RegisterUsersDto() {
		// TODO Auto-generated constructor stub
	}

	public RegisterUsersDto(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public Users toUsers() {
		Users users = new Users();
		users.setName(this.name);
		users.setEmail(this.email);
		return users;
	}


}
