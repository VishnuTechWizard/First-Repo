package com.example.RegisterLogin.Service.impl;

import com.example.RegisterLogin.response.LoginResponse;
import com.example.RegisterLogin.utility.JwtUtil;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.RegisterLogin.Dto.ResetPasswordDTO;
import com.example.RegisterLogin.Dto.UserDTO;
import com.example.RegisterLogin.Entity.Plans;
import com.example.RegisterLogin.Entity.User;
import com.example.RegisterLogin.Repo.PlanRepo;
import com.example.RegisterLogin.Repo.UserRepo;
import com.example.RegisterLogin.Service.UserService;

@Service
public class UserIMPL implements UserService {

	@Value("{$username}")
	private String fromMail;
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PlanRepo planRepo;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	JavaMailSender javaMailSender;

	@Override
	public LoginResponse addUser(UserDTO userDTO) {

		if (userRepo.existsByUsername(userDTO.getUsername())) {
			return new LoginResponse<>("1111", "Username is already taken.", null);
		}

		if (userRepo.existsByEmail(userDTO.getEmail())) {
			return new LoginResponse<>("1111", "Email is already taken.", null);
		}

		String id = System.currentTimeMillis() + "";

		User user = new User(id, userDTO.getUsername(), userDTO.getEmail(),
				this.passwordEncoder.encode(userDTO.getPassword()), userDTO.isStatus());
		userRepo.save(user);

		return new LoginResponse<>("0000", "Register Success", null);
	}

	@Override
	public LoginResponse addPlans(Plans plans) {
		Optional<User> user = userRepo.findByUsername(plans.getUsername());

		if (user.isPresent()) {
			User existingUser = user.get();

			if (existingUser.isStatus()) {
				return new LoginResponse("1111", "You are already subscribed", null);
			} else {
				String id = System.currentTimeMillis() + "";
				Plans plan = new Plans(id, plans.getUsername(), plans.getPlan(), plans.getPrice(),
						plans.getPriceType());
				planRepo.save(plan);

				existingUser.setStatus(true);
				userRepo.save(existingUser);

				return new LoginResponse("0000", "Subscribed successfully", null);
			}
		} else {
			return new LoginResponse("1111", "User not found", null);
		}
	}

	@Override
	public LoginResponse<?> login(HttpHeaders httpHeaders) {

		List<String> list = httpHeaders.get("Authorization");

		String encode = list.get(0);

		String[] get = encode.split(" ");
		String encodeValue = get[1];

		byte[] decode = Base64.getDecoder().decode(encodeValue);
		String decodeValue = new String(decode);
		String[] splitData = decodeValue.split(":");

		if (splitData.length != 2) {
			return new LoginResponse<>("1111", "Email and Password are required", null);
		}

		String email1 = splitData[0];
		String pass1 = splitData[1];

		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("userName", email1);
		String token = jwtUtil.createToken(claims, "test");
		User user = userRepo.findByEmail(email1);
		if (user != null) {
			String password = pass1;
			String encodedPassword = user.getPassword();
			Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
			if (isPwdRight) {
				String username = user.getUsername();
				boolean status = user.isStatus();

				Optional<User> user1 = userRepo.findOneByEmailAndPassword(email1, encodedPassword);

				if (user1.isPresent()) {
					LoginResponse.Data data = new LoginResponse.Data(username, token, status);
					return new LoginResponse<>("0000", "Login Success", data);

				} else {
					return new LoginResponse<>("1111", "Login Failed", null);
				}
			} else {
				return new LoginResponse<>("1111", "Password Not Match", null);
			}

		} else {
			return new LoginResponse<>("1111", "Email not exists", null);
		}
	}

	@Override
	public LoginResponse forgetPassword(UserDTO userDTO) {

		User user = userRepo.findByEmail(userDTO.getEmail());
		
		if(userDTO.getEmail().isEmpty()) {
			return new LoginResponse("1111", "Email is required", null);
		}

		if (user != null) {
			SimpleMailMessage message = new SimpleMailMessage();
			String link = "http://localhost:4200/reset-password";

			message.setFrom("test086412@gmail.com");
			message.setTo("test086412@gmail.com");
			message.setSubject("Subject: Sample mail");
			message.setText("This is Link For Change Your Password: " + "Link  " + link);

			javaMailSender.send(message);

			return new LoginResponse<>("0000", "mail send succesfully", null);
		} 
		else {
			return new LoginResponse("1111", "Email Doesn't Exist", null);

		}
	}

	@Override
	public LoginResponse resetPassword(ResetPasswordDTO resetPasswordDTO) {
		String email = resetPasswordDTO.getEmail();
		String newPassword = resetPasswordDTO.getNewPassword();
		String confirmPassword = resetPasswordDTO.getConfirmPassword();
		User user = userRepo.findByEmail(email);
		
		if(newPassword.isEmpty() && confirmPassword.isEmpty() ) {
			return new LoginResponse("0000","Password are requird",null);
		}

		if (!newPassword.equals(confirmPassword)) {
			return new LoginResponse<>("1111", "Passwords do not match", null);
		}

		if (user != null) {

			user.setPassword(passwordEncoder.encode(newPassword));
			userRepo.save(user);

			return new LoginResponse<>("0000", "Password reset successfully", null);
		} else {
			return new LoginResponse<>("1111", "User not found", null);
		}
	}

}
