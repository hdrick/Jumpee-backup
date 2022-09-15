package com.dricks.jumpee.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dricks.jumpee.helpers.FilterJsonView;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="order_history")
public class OrderHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	private int userId;
	@JsonView(FilterJsonView.class)
	private String orderNum;
	@JsonView(FilterJsonView.class)
	private String productName;
	@JsonView(FilterJsonView.class)
	private String productDesc;
	@JsonView(FilterJsonView.class)
	private int qty;
	@JsonView(FilterJsonView.class)
	private BigDecimal total;
	@JsonView(FilterJsonView.class)
	private LocalDateTime checkoutDate;
	@JsonView(FilterJsonView.class)
	private String status;
	
	
	public OrderHistory() {}

	public OrderHistory(int orderId, int userId,String orderNum, String productName, String productDesc, int qty,
			BigDecimal total, LocalDateTime checkoutDate, String status) {
		this.orderId = orderId;
		this.userId = userId;
		this.orderNum = orderNum;
		this.productName = productName;
		this.productDesc = productDesc;
		this.qty = qty;
		this.total = total;
		this.checkoutDate = checkoutDate;
		this.status = status;
	}

	public int getCheckoutId() {
		return orderId;
	}

	public void setCheckoutId(int checkoutId) {
		this.orderId = checkoutId;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public LocalDateTime getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDateTime checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderHistory [orderId=" + orderId + ", userId=" + userId + ", orderNum=" + orderNum + ", productName="
				+ productName + ", productDesc=" + productDesc + ", qty=" + qty + ", total=" + total + ", checkoutDate="
				+ checkoutDate + ", status=" + status + "]";
	}
}
