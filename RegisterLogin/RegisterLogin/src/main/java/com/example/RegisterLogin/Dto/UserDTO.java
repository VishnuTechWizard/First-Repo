package com.example.RegisterLogin.Dto;

public class UserDTO {
	
	private String username;
	private String email;
	private String password;
	private String mobileno;
	private boolean status;
	private String userOtp;
	

	public UserDTO(String username, String email, String password, String mobileno, boolean status, String userOtp) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.mobileno = mobileno;
		this.status = status;
		this.userOtp = userOtp;
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
	
	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

	public String getUserOtp() {
		return userOtp;
	}

	public void setUserOtp(String userOtp) {
		this.userOtp = userOtp;
	}

	@Override
	public String toString() {
		return "UserDTO [username=" + username + ", email=" + email + ", password=" + password + ", mobileno="
				+ mobileno + ", status=" + status + ", userOtp=" + userOtp + "]";
	}
	
}
