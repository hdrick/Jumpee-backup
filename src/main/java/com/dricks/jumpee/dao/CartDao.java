package com.dricks.jumpee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dricks.jumpee.entity.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer>{
	
    @Query(value = "SELECT * FROM cart where user_id=:userId AND status='PENDING'", nativeQuery = true)
    List<Cart> findCartAll(@Param("userId") int userId);
	
	Cart findCartByOrderNumber(String orderNum);
	Cart findCartByUserId(int id);
}
