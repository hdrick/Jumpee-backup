package com.dricks.jumpee.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dricks.jumpee.helpers.FilterJsonView;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	private int userId;
	private int prodId;
	@JsonView(FilterJsonView.class)
	private String productName;
	@JsonView(FilterJsonView.class)
	private int qty;
	@JsonView(FilterJsonView.class)
	private BigDecimal productPrice;
	@JsonView(FilterJsonView.class)
	private BigDecimal total;
	@JsonView(FilterJsonView.class)
	private String status;
	@JsonView(FilterJsonView.class)
	private String orderNumber;
	
	public Cart() {}
	
	public Cart(int cartId, int userId, int prodId, String productName, int qty, BigDecimal productPrice,
			BigDecimal total, String status, String orderNumber) {
		this.cartId = cartId;
		this.userId = userId;
		this.prodId = prodId;
		this.productName = productName;
		this.qty = qty;
		this.productPrice = productPrice;
		this.total = total;
		this.status = status;
		this.orderNumber = orderNumber;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", userId=" + userId + ", prodId=" + prodId + ", productName=" + productName
				+ ", qty=" + qty + ", productPrice=" + productPrice + ", total=" + total + ", status=" + status
				+ ", orderNumber=" + orderNumber + "]";
	}
}
