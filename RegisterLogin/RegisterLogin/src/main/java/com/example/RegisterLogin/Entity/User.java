package com.example.RegisterLogin.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document

public class User {
	
	@Id
	private String id;
	private String username;
	private String email;
	private String password;
	private String mobileno;
	private boolean status;


	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public User() {
		super();	
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
	
	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public User(String id, String username, String email, String password, String mobileno, boolean status) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.mobileno = mobileno;
		this.status = status;
	}

}
