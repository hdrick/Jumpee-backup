package com.dricks.jumpee.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dricks.jumpee.dao.OrderHistoryDao;
import com.dricks.jumpee.entity.Cart;
import com.dricks.jumpee.entity.OrderHistory;
import com.dricks.jumpee.helpers.UserLoginInfo;

@Service
public class OrderHistoryService {
	
	@Autowired
	private OrderHistoryDao orderHistoryDao;
	
	public void addOrHistory(Cart cart, String prodName) {
		OrderHistory orHistory = new OrderHistory();
		
		orHistory.setCheckoutDate(LocalDateTime.now());
		orHistory.setOrderNum(cart.getOrderNumber());
		orHistory.setProductDesc(prodName);
		orHistory.setProductName(cart.getProductName());
		orHistory.setQty(cart.getQty());
		orHistory.setTotal(cart.getTotal());
		orHistory.setStatus("PAID");
		orHistory.setUserId(cart.getUserId());
		
		orderHistoryDao.save(orHistory);
	}
	
	
	public List <OrderHistory> getAllOrderHistory() {
		List<OrderHistory> theOrHistory = orderHistoryDao.findByUserId(UserLoginInfo.userGlobalId);
		return theOrHistory;
	}
}
