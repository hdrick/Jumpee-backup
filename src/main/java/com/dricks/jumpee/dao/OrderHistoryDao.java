package com.dricks.jumpee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dricks.jumpee.entity.OrderHistory;

@Repository
public interface OrderHistoryDao extends JpaRepository<OrderHistory, Integer>{
	List <OrderHistory> findByUserId(int id);
}
