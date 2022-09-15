package com.dricks.jumpee.entity;

public class Checkout {

	private String orderNumber;

	public Checkout() {}

	public Checkout(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Override
	public String toString() {
		return "Checkout [orderNumber=" + orderNumber + "]";
	}
}
