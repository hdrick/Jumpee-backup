package com.dricks.jumpee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dricks.jumpee.helpers.FilterJsonView;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private int addressId;
	private int userId;
	
	@JsonView(FilterJsonView.class)
	private String address;
	@JsonView(FilterJsonView.class)
	private String contactPerson;
	@JsonView(FilterJsonView.class)
	private String contactNumber;
	
	public Address() {}
	
	public Address(int addressId, String address, String contactPerson, String contactNumber) {
		this.addressId = addressId;
		this.address = address;
		this.contactPerson = contactPerson;
		this.contactNumber = contactNumber;
	}
	
	public Address(int addressId, int userId, String address, String contactPerson, String contactNumber) {
		this.addressId = addressId;
		this.userId = userId;
		this.address = address;
		this.contactPerson = contactPerson;
		this.contactNumber = contactNumber;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public int getUser_id() {
		return userId;
	}

	public void setUser_id(int user_id) {
		this.userId = user_id;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", userId=" + userId + ", address=" + address + ", contactPerson="
				+ contactPerson + ", contactNumber=" + contactNumber + "]";
	}

}
