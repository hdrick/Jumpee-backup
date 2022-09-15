package com.dricks.jumpee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.dricks.jumpee.entity.ChangePassword;
import com.dricks.jumpee.entity.User;
import com.dricks.jumpee.helpers.FilterJsonView;
import com.dricks.jumpee.helpers.UnauthorizedUser;
import com.dricks.jumpee.helpers.UserLoginInfo;
import com.dricks.jumpee.response.MessageResponse;
import com.dricks.jumpee.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	//..................Start : POSTMAPPING REGISTER USER....................
	@PostMapping("Register-User")
	public ResponseEntity<?> registerNewUser(@Valid @RequestBody User user){
		boolean checkEmailIfExist = userService.isEmailExist(user.getEmail());
		String pass = user.getPassword();
		String pass2 = user.getConfirmPassword();

		if(checkEmailIfExist == true) {
			return ResponseEntity.ok().body(new MessageResponse("Error: Email is already Exist."));
		}
		else{
			if(pass.equals(pass2)) {
				userService.resgisterNewUser(user);
				return ResponseEntity.ok().body(new MessageResponse("Success: You have been successfully registered."));
			}else {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Passwords do not match."));
			}
		}
	}
	
	//..................Start : POSTMAPPING FORGOT PASSWORD....................
	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestBody User user) {

		String response = userService.forgotPassword(user.getEmail());

		if (!response.startsWith("Invalid")) {
			return ResponseEntity.badRequest().body(new MessageResponse(response));
		}else {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid email address!"));
		}
	}
	

	@PutMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody User user) {

		return userService.resetPassword(user.getToken(), user.getPassword());
	}
	
	//..................Start : GET USER details  For registered user only  ....................
	@JsonView(FilterJsonView.class)
	@GetMapping("/user-details")
	public ResponseEntity<?> getUserDetails(@RequestHeader HttpHeaders headers){
		String email =  UserLoginInfo.userGlobalEmail;
		User user = null;
		
		if(email != null) {		
			user = userService.getUserDetails(email);
			return new ResponseEntity<>(user, HttpStatus.OK);
		}else {	    
			return new ResponseEntity<>(UnauthorizedUser.getMessage("/user-details"), HttpStatus.UNAUTHORIZED);
		}
	}
	
	//..................Start : Change password For registered user only  ....................
	@PutMapping("/change-password")
	public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePassword thePass){
		String email =  UserLoginInfo.userGlobalEmail;// user email
		
		
		
		if(email != null) {		
			 ResponseEntity<?> mesg = userService.changePassword(thePass);
			 return mesg;
			
		}else {	    
			return new ResponseEntity<>(UnauthorizedUser.getMessage("/change-password"), HttpStatus.UNAUTHORIZED);
		}
	}
}
