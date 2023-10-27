package com.example.RegisterLogin.Service;

import com.example.RegisterLogin.Dto.ResetPasswordDTO;
import com.example.RegisterLogin.Dto.UserDTO;
import com.example.RegisterLogin.Entity.Plans;
import com.example.RegisterLogin.Entity.User;
import com.example.RegisterLogin.response.LoginResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

//	String addUser(UserDTO userDTO);

	LoginResponse login(HttpHeaders httpHeaders);
	
	LoginResponse addUser(UserDTO userDTO);
	
	LoginResponse addPlans(Plans plans);

	LoginResponse forgetPassword(UserDTO userDTO);

    LoginResponse resetPassword(ResetPasswordDTO resetPasswordDTO);

}
