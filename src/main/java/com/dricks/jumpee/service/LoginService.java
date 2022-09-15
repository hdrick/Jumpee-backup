package com.dricks.jumpee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dricks.jumpee.dao.LoginDao;
import com.dricks.jumpee.entity.LoginUser;

@Service
public class LoginService {
	@Autowired
	private LoginDao loginDao;
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	//..................Login..................
	public LoginUser login(String email, String password) {
		LoginUser userDb = loginDao.findByEmail(email);
		
		if(userDb != null) {
			boolean result = passwordEncoder.matches(password,userDb.getPassword());
			if(result == true) {
				return userDb;
			}else {
				return null;
			}
		}else {
			return null;
		}	
	}
	
	public LoginUser findByEmail(String email) {
		LoginUser theUser = loginDao.findByEmail(email);
		return theUser;
	}
	
}
