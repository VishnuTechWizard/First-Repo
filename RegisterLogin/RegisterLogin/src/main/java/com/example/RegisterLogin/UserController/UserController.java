package com.example.RegisterLogin.UserController;


import com.example.RegisterLogin.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.RegisterLogin.Dto.ResetPasswordDTO;
import com.example.RegisterLogin.Dto.UserDTO;
import com.example.RegisterLogin.Entity.Plans;
import com.example.RegisterLogin.Service.UserService;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	   @Autowired
	    JavaMailSender javaMailSender;


	@PostMapping(value="/save")
	public LoginResponse saveUser(@RequestBody UserDTO userDTO) {
		return userService.addUser(userDTO);
	}
	
	@RequestMapping(value="/plans")
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
	
//	@PostMapping("/reset-pass")
//	public LoginResponse resetPass(@RequestBody UserDTO userDTO) {
//		return userService.resetPassword(userDTO,null);
//	}
//    @PostMapping("/reset-pass")
//    public LoginResponse resetPass(@RequestBody UserDTO userDTO, @RequestParam String newPassword) {
//        return userService.resetPassword(userDTO, newPassword);
//    }
	
	@PostMapping("/reset-pass")
	public LoginResponse resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
	    return userService.resetPassword(resetPasswordDTO);
	}
	
}
