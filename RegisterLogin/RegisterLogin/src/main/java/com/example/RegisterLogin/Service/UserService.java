package com.example.RegisterLogin.Service;

import com.example.RegisterLogin.Dto.ResetPasswordDTO;
import com.example.RegisterLogin.Dto.UserDTO;
import com.example.RegisterLogin.Entity.Plans;
import com.example.RegisterLogin.Entity.User;
import com.example.RegisterLogin.Entity.UserDetails;
import com.example.RegisterLogin.otpResponse.OtpResponse;
import com.example.RegisterLogin.response.LoginResponse;
import com.example.RegisterLogin.response.Result;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

	LoginResponse login(HttpHeaders httpHeaders);
	
	Result addUser(User user);
	
	LoginResponse addPlans(Plans plans);

	LoginResponse forgetPassword(UserDTO userDTO);

    LoginResponse resetPassword(ResetPasswordDTO resetPasswordDTO);
    
    LoginResponse otpSend(UserDTO userDTO);

    LoginResponse verifyOtp(UserDTO userDTO);

	Result userDetails(UserDetails userDetails);


	Result getUserDetails(String id);

}
