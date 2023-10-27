package com.example.RegisterLogin.Dto;

public class UserDTO {
	
	private String username;
	private String email;
	private String password;
	private boolean status;
	
	public UserDTO(String username, String email, String password, boolean status) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.status = status;
	}

	public UserDTO() {
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
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "UserDTO [ username=" + username + ", email=" + email + ", password=" + password
				+ "]";
	}

}
