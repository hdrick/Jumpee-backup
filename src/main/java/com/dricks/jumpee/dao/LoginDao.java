package com.dricks.jumpee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dricks.jumpee.entity.LoginUser;


@Repository
public interface LoginDao extends JpaRepository<LoginUser, Integer>{
	LoginUser findByEmailAndPassword(String email, String password);
	
	LoginUser findByEmail(String email);
}
