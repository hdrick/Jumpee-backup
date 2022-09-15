package com.dricks.jumpee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dricks.jumpee.dao.AddressDao;
import com.dricks.jumpee.entity.Address;
import com.dricks.jumpee.helpers.UserLoginInfo;

@Service
public class AddressService {

	@Autowired
	private AddressDao addressDao;
	
	public void addUserAddress(Address address, int id) {
		address.setUser_id(id);
		addressDao.save(address);
	}
	
	public void removeUserAddress(int id) {
		Address addressId = this.getAddressDetails(id);//get addressID
		addressDao.deleteById(addressId.getAddressId());
	}
	
	public Address updateUserAddress(Address address, int id) {
		Address addressExist = getAddressDetails(id);
		
		addressExist.setAddress(address.getAddress());
		addressExist.setContactPerson(address.getContactPerson());
		addressExist.setContactNumber(address.getContactNumber());
		
		return addressDao.save(addressExist);
	}
	
	public Address getAddressDetails(int id) {
		return addressDao.findByUserId(id);
	}
	
	public Address displayUserAddress() {
		return this.getAddressDetails(UserLoginInfo.userGlobalId);
	}
}
