package com.dricks.jumpee.controller;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dricks.jumpee.entity.LoginUser;
import com.dricks.jumpee.helpers.UserLoginInfo;
import com.dricks.jumpee.response.MessageResponse;
import com.dricks.jumpee.service.LoginService;

@RestController
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginUser user){
		LoginUser checkUser =  loginService.login(user.getEmail(), user.getPassword());
		LoginUser getFL= loginService.findByEmail(user.getEmail());
		
		if(Objects.nonNull(checkUser)) {
			UserLoginInfo.setUserGlobalEmail(user.getEmail());
			UserLoginInfo.setUserGlobalId(checkUser.getUser_id());
			return ResponseEntity.ok().body(new MessageResponse("Success: Login Success: welcome "
											+getFL.getFirstName()+" "+getFL.getLastName()));
		}else {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Wrong Email or Password!"));
		}	
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(){
		UserLoginInfo.setUserGlobalEmail(null);
		return ResponseEntity.ok().body(new MessageResponse("Success: You have been successfully "
															+ "logged out."));
	}
	

}
