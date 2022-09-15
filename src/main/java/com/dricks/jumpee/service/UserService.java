package com.dricks.jumpee.service;

import java.time.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dricks.jumpee.dao.UserDao;
import com.dricks.jumpee.entity.ChangePassword;
import com.dricks.jumpee.entity.User;
import com.dricks.jumpee.helpers.UserLoginInfo;
import com.dricks.jumpee.response.MessageResponse;

@Service
public class UserService {
	
	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 10;
	
	@Autowired
	private UserDao userDao;
	

	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	//..................Register new user/customer.......................
	public void resgisterNewUser(User user) {
		String encryptedPassword = passwordEncoder.encode(user.getPassword());
		String encryptedPassword2 = passwordEncoder.encode(user.getConfirmPassword());
		
		String firstname = user.getFirstName();
		String lastname = user.getLastName();
		
		String capFirstname = firstname.substring(0, 1).toUpperCase() + firstname.substring(1);
		String capLastname = lastname.substring(0, 1).toUpperCase() + lastname.substring(1);
		
		
		user.setFirstName(capFirstname);
		user.setLastName(capLastname);
		user.setPassword(encryptedPassword);
		user.setConfirmPassword(encryptedPassword2);
		
		userDao.save(user);
	}
	
	//..................Get User Details..................
	public User getUserDetails(String email) {	
		User user = userDao.findByEmail(email);	
		return user;	
	}
	
	//..................Find Email if Exist in Database..................
	public boolean isEmailExist(String email) {
		User isExist = userDao.findByEmail(email);
		if(isExist!= null) {
			return true;
		}else {
			return false;
		}
	}
	
	//..................Forgot Password..................................
	public String forgotPassword(String email) {

		Optional<User> userOptional = Optional
				.ofNullable(userDao.findByEmail(email));

		if (!userOptional.isPresent()) {
			return "Invalid email id.";
		}

		User user = userOptional.get();
		user.setToken(generateToken());
		user.setTokenCreationDate(LocalDateTime.now());

		user = userDao.save(user);

		return user.getToken();
	}
	
	//..................Reset Password...................................
	public ResponseEntity<?> resetPassword(String token, String password) {

		Optional<User> userOptional = Optional
				.ofNullable(userDao.findByToken(token));

		if (!userOptional.isPresent()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid Token."));
		}

		LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

		if (isTokenExpired(tokenCreationDate)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Token expired."));

		}

		User user = userOptional.get();
		
		String encryptedPassword = passwordEncoder.encode(password);
		user.setPassword(encryptedPassword);
		user.setToken(null);
		user.setTokenCreationDate(null);

		userDao.save(user);

		return ResponseEntity.ok().body(new MessageResponse("Success: Your password successfully updated."));
	}
	
	//..................Generate TOKEN...................................
	private String generateToken() {
		StringBuilder token = new StringBuilder();

		return token.append(UUID.randomUUID().toString())
				.append(UUID.randomUUID().toString()).toString();
	}
	
	//..................Generate TOKEN Expired or not....................
	private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

		LocalDateTime now = LocalDateTime.now();
		Duration diff = Duration.between(tokenCreationDate, now);

		return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
	}
	
	
	public ResponseEntity<?> changePassword(ChangePassword thePass){
		User user = userDao.findByEmail(UserLoginInfo.userGlobalEmail);
		String currentPass = user.getConfirmPassword();
		String inputCurrentPass = thePass.getCurrentPassword();
		String inputNewPass= thePass.getNewPassword();
		String inputConfirmPass = thePass.getConfirmPassword();
		 ResponseEntity<?> mesg = null;
		
		
		boolean ifMatchPass = passwordEncoder.matches(inputCurrentPass, currentPass);
		
		if(ifMatchPass == true && inputNewPass.equals(inputConfirmPass)) {
			
			user.setPassword(passwordEncoder.encode(inputNewPass));
			return mesg = ResponseEntity.ok().body(new MessageResponse
					("Success: Your password has been changed successfully."));
		}
		else if(ifMatchPass == true && !(inputNewPass.equals(inputConfirmPass))) {
			return mesg = ResponseEntity.badRequest().body(new MessageResponse
								("Error: Your New password and Confirm password do not match!"));
		}
		else if(ifMatchPass == false) {
			return mesg =  ResponseEntity.badRequest().body(new MessageResponse
								("Error: Your Current password is inncorrect!"));
		}
		return mesg;
	}
}
