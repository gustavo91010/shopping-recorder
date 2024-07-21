package com.ajudaqui.recalldecompras.client.dto;

import java.time.LocalDateTime;

public class UsersDTO {
	private Long id;
	private String name;
	private String email;
	private LocalDateTime created_at;
	private LocalDateTime updated_at ;
	
	
	
	public UsersDTO() {
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	public LocalDateTime getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", email=" + email + ", created_at=" + created_at
				+ ", updated_at=" + updated_at + "]";
	}
	
	
	

}
