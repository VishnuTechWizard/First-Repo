package com.example.RegisterLogin.response;



public class Result<T> {
	private String code;
	private String message;
	private T data;


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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Result(String code, String message, T data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
