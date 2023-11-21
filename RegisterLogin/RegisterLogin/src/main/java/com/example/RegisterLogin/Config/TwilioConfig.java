package com.example.RegisterLogin.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twilio")
public class TwilioConfig {

	private String accountSid;
	private String authToken;
	private String phoneno;
	public String getAccountSid() {
		return accountSid;
	}
	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public TwilioConfig(String accountSid, String authToken, String phoneno) {
		super();
		this.accountSid = accountSid;
		this.authToken = authToken;
		this.phoneno = phoneno;
	}
	public TwilioConfig() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	

}
