package com.example.RegisterLogin.otpResponse;

public class OtpResponse {

	private String code;
	private String message;
	private String username;
	private boolean status;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}


	public OtpResponse(String code, String message, String username, boolean status) {
		super();
		this.code = code;
		this.message = message;
		this.username = username;
		this.status = status;
	}

	public OtpResponse() {
		super();
	}

}
