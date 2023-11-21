package com.example.RegisterLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


import com.example.RegisterLogin.Config.TwilioConfig;
import com.twilio.Twilio;

import jakarta.annotation.PostConstruct;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class RegisterLoginApplication {
	
	@Autowired
	private TwilioConfig twilioConfig;
	
	@PostConstruct
	public void initTwilio() {
	  Twilio.init(twilioConfig.getAccountSid(),twilioConfig.getAuthToken());
	}
	

	
	public static void main(String[] args) {
		SpringApplication.run(RegisterLoginApplication.class, args);
	}

}
