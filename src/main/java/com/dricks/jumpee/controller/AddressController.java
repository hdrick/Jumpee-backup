package com.dricks.jumpee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dricks.jumpee.entity.Address;
import com.dricks.jumpee.entity.User;
import com.dricks.jumpee.helpers.FilterJsonView;
import com.dricks.jumpee.helpers.UnauthorizedUser;
import com.dricks.jumpee.helpers.UserLoginInfo;
import com.dricks.jumpee.response.MessageResponse;
import com.dricks.jumpee.service.AddressService;
import com.dricks.jumpee.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/add-address")
	public ResponseEntity<?> addUserAddress(@RequestBody Address address){
		String email = UserLoginInfo.userGlobalEmail; //current email of user
		int userId=0;// current id of user.

		if(email !=null) {
			User userEmail = userService.getUserDetails(email);
			userId = userEmail.getUser_id();
			addressService.addUserAddress(address, userId);
			return ResponseEntity.ok().body(new MessageResponse("Success: Your address has successfully added."));
		}else {

			return new ResponseEntity<>(UnauthorizedUser.getMessage("/add-address"), HttpStatus.UNAUTHORIZED);
		}	
	}
	
	@JsonView(FilterJsonView.class)
	@PutMapping("/update-address")
	public ResponseEntity<?> updateUserAddress(@RequestBody Address theAddress) {
		String email = UserLoginInfo.userGlobalEmail; //current email of user
		int userId=0;// current id of user.
		
		if(email !=null) {
			User userEmail = userService.getUserDetails(email);
			userId = userEmail.getUser_id();

			addressService.updateUserAddress(theAddress, userId);
			return new ResponseEntity<>(theAddress, HttpStatus.OK);
		}else {

			return new ResponseEntity<>(UnauthorizedUser.getMessage("/update-address"), HttpStatus.UNAUTHORIZED);
		}	
	}
	
	
	@DeleteMapping("/delete-address")
	public ResponseEntity<?> deleteUserAddress(){
		String email = UserLoginInfo.userGlobalEmail; //current email of user

		if(email !=null) {
			User userEmail = userService.getUserDetails(email);
			
			addressService.removeUserAddress(userEmail.getUser_id());
			return ResponseEntity.ok().body(new MessageResponse("Success: Your address has successfully deleted."));
		}else {

			return new ResponseEntity<>(UnauthorizedUser.getMessage("/delete-address"), HttpStatus.UNAUTHORIZED);
		}	
	}
	
	@JsonView(FilterJsonView.class)
	@GetMapping("/user-address")
	public ResponseEntity<?> displayUserAddress(){
		String email = UserLoginInfo.userGlobalEmail; //current email of user
		
		if(email !=null) {	
			Address thAddress = addressService.displayUserAddress();
			return ResponseEntity.ok().body(thAddress);
		}else {

			return new ResponseEntity<>(UnauthorizedUser.getMessage("/user-address"), HttpStatus.UNAUTHORIZED);
		}	
	}
}
