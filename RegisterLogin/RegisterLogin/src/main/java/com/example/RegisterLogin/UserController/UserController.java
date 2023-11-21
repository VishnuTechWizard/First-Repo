package com.example.RegisterLogin.UserController;

import com.example.RegisterLogin.response.LoginResponse;
import com.example.RegisterLogin.response.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.RegisterLogin.Dto.ResetPasswordDTO;
import com.example.RegisterLogin.Dto.UserDTO;
import com.example.RegisterLogin.Entity.Plans;
import com.example.RegisterLogin.Entity.User;
import com.example.RegisterLogin.Entity.UserDetails;
import com.example.RegisterLogin.Service.UserService;
import com.example.RegisterLogin.otpResponse.OtpResponse;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/user")

public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	JavaMailSender javaMailSender;

	@PostMapping(value = "/save")
	public Result saveUser(@RequestBody User user) {
		return userService.addUser(user);
	}

	@RequestMapping(value = "/plans")
	public LoginResponse savePlans(@RequestBody Plans plans) {
		return userService.addPlans(plans);
	}

	@GetMapping(value = "/login")
	public LoginResponse login(@RequestHeader HttpHeaders httpHeaders) {
		return userService.login(httpHeaders);
	}

	@PostMapping("/forgot-pass")
	public LoginResponse forgotPass(@RequestBody UserDTO userDTO) {
		return userService.forgetPassword(userDTO);
	}

	@PostMapping("/reset-pass")
	public LoginResponse resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
		return userService.resetPassword(resetPasswordDTO);
	}

	@PostMapping(value = "/sendOtp")
	public LoginResponse otpSend(@RequestBody UserDTO userDTO) {
		return userService.otpSend(userDTO);
	}

	@PostMapping(value = "/verifyOtp")
	public LoginResponse verifyOtp(@RequestBody UserDTO userDTO) {
		return userService.verifyOtp(userDTO);
	}
	
	@PostMapping(value="/userDetails")
	public Result userDetails(@RequestBody UserDetails userDetails) {
		return userService.userDetails(userDetails);
	}
	
	@GetMapping(value="/getUserDetails/{user_id}")
	public Result getUserDetails(@PathVariable String user_id) {
		return userService.getUserDetails(user_id);
	}
	




}
