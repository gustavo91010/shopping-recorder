package com.ajudaqui.recalldecompras.dto;

import com.ajudaqui.recalldecompras.entity.Users;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UsersDto {
	 @JsonProperty("name")
	private String name;
	    @JsonProperty("email")
	private String email;
	    @JsonProperty("password")
	private String password;
public UsersDto() {
	// TODO Auto-generated constructor stub
}	
	
	public UsersDto(String name, String email, String password) {
	super();
	this.name = name;
	this.email = email;
	this.password = password;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Users toUsers() {
		Users users= new Users();
		users.setName(this.name);
		users.setEmail(this.email);
		users.setPassword(this.password);
		return users;
	}
	@Override
	public String toString() {
		return "UsersDto [name=" + name + ", email=" + email + ", password=" + password + "]";
	}

}
