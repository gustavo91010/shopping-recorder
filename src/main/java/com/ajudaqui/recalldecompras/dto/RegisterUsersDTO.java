package com.ajudaqui.recalldecompras.dto;

import com.ajudaqui.recalldecompras.client.dto.UsersDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterUsersDTO {
	@JsonProperty("name")
	private String name;
	@JsonProperty("email")
	private String email;

	public RegisterUsersDTO() {
		// TODO Auto-generated constructor stub
	}

	public RegisterUsersDTO(String name, String email) {
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


	public UsersDTO toUsers() {
		UsersDTO users = new UsersDTO();
		users.setName(this.name);
		users.setEmail(this.email);
		return users;
	}


}
