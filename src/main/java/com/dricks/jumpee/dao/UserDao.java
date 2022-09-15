package com.dricks.jumpee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dricks.jumpee.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	User findByEmailAndPassword(String email, String password);
	
	User findByEmail(String email);
	
	User findByToken(String token);
}
