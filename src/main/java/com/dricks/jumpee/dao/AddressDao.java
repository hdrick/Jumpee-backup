package com.dricks.jumpee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dricks.jumpee.entity.Address;

@Repository
public interface AddressDao extends JpaRepository<Address, Integer>{
	 Address findByUserId(int id);
}
