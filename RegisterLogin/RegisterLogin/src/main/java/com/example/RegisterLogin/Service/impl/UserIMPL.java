package com.example.RegisterLogin.Service.impl;

import com.example.RegisterLogin.response.LoginResponse;
import com.example.RegisterLogin.response.Result;
import com.example.RegisterLogin.utility.JwtUtil;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.message.MediaDeleter;
import com.twilio.type.PhoneNumber;

import java.text.DecimalFormat;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.RegisterLogin.Config.TwilioConfig;
import com.example.RegisterLogin.Dto.ResetPasswordDTO;
import com.example.RegisterLogin.Dto.UserDTO;
import com.example.RegisterLogin.Entity.MedicalDetails;
import com.example.RegisterLogin.Entity.Plans;
import com.example.RegisterLogin.Entity.ProfessionalDetails;
import com.example.RegisterLogin.Entity.User;
import com.example.RegisterLogin.Entity.UserDetails;
import com.example.RegisterLogin.Repo.PlanRepo;
import com.example.RegisterLogin.Repo.UserDetailsRepo;
import com.example.RegisterLogin.Repo.UserRepo;
import com.example.RegisterLogin.Service.UserService;
import com.example.RegisterLogin.otpResponse.OtpResponse;

@Service
public class UserIMPL implements UserService {

	@Value("{$username}")
	private String fromMail;
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PlanRepo planRepo;

	@Autowired
	private UserDetailsRepo userDetailsRepo;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	private TwilioConfig twilioConfig;

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public Result addUser(User user) {

		Optional<User> existingUser = userRepo.findByUsername(user.getUsername());

		if (!existingUser.isPresent()) {
			String id = System.currentTimeMillis() + "";

			if (userRepo.existsByEmail(user.getEmail())) {
				return new Result("1111", "Email is already taken.", null);
			}

			User newUser = new User(id, user.getUsername(), user.getEmail(),
					this.passwordEncoder.encode(user.getPassword()), user.getMobileno(), user.isStatus());
			userRepo.save(newUser);
			return new Result("0000", "Rregistration complete", newUser);

		} else {
			User updateUser = existingUser.get();
			updateUser.setId(user.getId());
			updateUser.setEmail(user.getEmail());
			if (!user.getPassword().isEmpty()) {
				updateUser.setPassword(this.passwordEncoder.encode(user.getPassword()));
			}
			updateUser.setMobileno(user.getMobileno());
			userRepo.save(updateUser);

			return new Result("0000", "Registration Updated", existingUser);
		}

	}

	@Override
	public LoginResponse addPlans(Plans plans) {
		Optional<User> user = userRepo.findByUsername(plans.getUsername());

		if (user.isPresent()) {
			User existingUser = user.get();

			if (existingUser.isStatus()) {
				return new LoginResponse("1111", "You are already subscribed", null);
			}

			else if (plans.getPrice() < 1) {
				String id = System.currentTimeMillis() + "";
				Plans plan = new Plans(id, plans.getUsername(), plans.getPlan(), plans.getPrice(), null);
				planRepo.save(plan);

				existingUser.setStatus(true);
				userRepo.save(existingUser);

				return new LoginResponse("0000", "Subscribed successfully", null);

			}

			else {
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
				String id = user.getId();
				String username = user.getUsername();
				boolean status = user.isStatus();

				Optional<User> user1 = userRepo.findOneByEmailAndPassword(email1, encodedPassword);

				if (user1.isPresent()) {
					LoginResponse.Data data = new LoginResponse.Data(id, username, token, status);
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

		if (userDTO.getEmail().isEmpty()) {
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
		} else {
			return new LoginResponse("1111", "Email Doesn't Exist", null);

		}
	}

	@Override
	public LoginResponse resetPassword(ResetPasswordDTO resetPasswordDTO) {
		String email = resetPasswordDTO.getEmail();
		String newPassword = resetPasswordDTO.getNewPassword();
		String confirmPassword = resetPasswordDTO.getConfirmPassword();
		User user = userRepo.findByEmail(email);

		if (newPassword.isEmpty() && confirmPassword.isEmpty()) {
			return new LoginResponse("0000", "Password are requird", null);
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

	Map<String, String> otpValue = new HashMap<>();

	@Override
	public LoginResponse otpSend(UserDTO userDTO) {
		PhoneNumber to = new PhoneNumber(userDTO.getMobileno());
		PhoneNumber from = new PhoneNumber(twilioConfig.getPhoneno());
		String otp = generateOTP();
		String otpMessage = "Hi Your Verification Code is " + otp + ".Don't Share With Anyone.";

		Message message = Message.creator(to, from, otpMessage).create();
		otpValue.put(userDTO.getMobileno(), otp);

		return new LoginResponse("0000", "Otp Send Sucessfully", null);
	}

	@Override
	public LoginResponse verifyOtp(UserDTO userDTO) {

		User user = userRepo.findByMobileno(userDTO.getMobileno());

		String id = user.getId();
		String username = user.getUsername();
		boolean status = user.isStatus();

		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("userName", username);
		String token = jwtUtil.createToken(claims, "test");

		LoginResponse.Data data = new LoginResponse.Data(id, username, token, status);

		if (userDTO.getUserOtp().equals(otpValue.get(userDTO.getMobileno()))) {
			return new LoginResponse("0000", "verified Sucessfully", data);
		} else {
			return new LoginResponse("1111", "invalid Otp", null);
		}

	}

	private String generateOTP() {
		return new DecimalFormat("0000").format(new Random().nextInt(9999));
	}

	@Override
	public Result userDetails(UserDetails userDetails) {

		Optional<UserDetails> userId = userDetailsRepo.findById(userDetails.getId());
		if (userId.isPresent()) {
			
			UserDetails user = userId.get();
			
			if (userDetails.getPersonalDetails() != null) {
				user.setPersonalDetails(userDetails.getPersonalDetails());
//				user.setStatus(userDetails.getStatus());			
			}
			
			if (userDetails.getProfessionalDetails() != null) {
				user.setProfessionalDetails(userDetails.getProfessionalDetails());
//				user.setStatus(userDetails.getStatus());

			}

			if (userDetails.getMedicalDetails() != null) {
				user.setMedicalDetails(userDetails.getMedicalDetails());
//				user.setStatus(userDetails.getStatus());
			}
			
			user.setStatus(userDetails.getStatus());
			user.setFormStatus(userDetails.getFormStatus());;


		
			userDetailsRepo.save(userId.get());
        
		return new Result ("0000","update",userDetails);

		}
		return null;

	}

	@Override
	public Result getUserDetails(String user_id) {

		UserDetails details = mongoOperations.findOne(new Query(Criteria.where("user_id").is(user_id)),
				UserDetails.class);

		if (details == null) {

			String id = System.currentTimeMillis() + "";

			UserDetails userDetails = new UserDetails();

			userDetails.setUser_id(user_id);
//			 userDetails.getPersonalDetails().setUser_id(user_id);
			userDetails.setId(id);
//			 userDetails.getPersonalDetails().setId(id);


			userDetailsRepo.save(userDetails);

			return new Result("0000", "created", userDetails);
//			return new Result ("1111","user not found",null);
		} else {
			return new Result("0000", "value present", details);
		}

	}

}
