package com.dricks.jumpee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dricks.jumpee.entity.Cart;
import com.dricks.jumpee.entity.Checkout;
import com.dricks.jumpee.entity.OrderHistory;
import com.dricks.jumpee.helpers.FilterJsonView;
import com.dricks.jumpee.helpers.UnauthorizedUser;
import com.dricks.jumpee.helpers.UserLoginInfo;
import com.dricks.jumpee.response.MessageResponse;
import com.dricks.jumpee.service.CartService;
import com.dricks.jumpee.service.OrderHistoryService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	OrderHistoryService orHisService;

	@PostMapping("/add-to-cart")
	public ResponseEntity<?> addTocart(@RequestBody Cart theCart){
		String email =  UserLoginInfo.userGlobalEmail;// user email
		
		if(email != null) {		
			ResponseEntity<?> theResponse = cartService.addToCart(theCart);
			System.out.print(theResponse);
			if(theResponse != null) {
				return ResponseEntity.badRequest().body(new MessageResponse("Success: Product successfully added "
						+ "to your shopping cart"));
			}else {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Product didn't exist. Make sure product name is correct!"));
			}
				
		}else {	    
			return new ResponseEntity<>(UnauthorizedUser.redirectToLogin("/add-to-cart"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/checkout-product")
	public ResponseEntity<?> checkoutProduct(@RequestBody Checkout theOrderNum){
		String email =  UserLoginInfo.userGlobalEmail;// user email
		
		if(email != null) {		
			ResponseEntity<?> mesg = cartService.checkOut(theOrderNum);
			return mesg;

		}else {	    
			return new ResponseEntity<>(UnauthorizedUser.redirectToLogin("/add-to-cart"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@JsonView(FilterJsonView.class)
	@GetMapping("/user-cart")
	public ResponseEntity<?> displayUserCart(){	
		
		String email =  UserLoginInfo.userGlobalEmail;// user email
		
		if(email != null) {		
			List <Cart> userCart = cartService.getUserCart();
			return ResponseEntity.ok().body(userCart);
		}else {	    
			return new ResponseEntity<>(UnauthorizedUser.getMessage("/user-cart"), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@JsonView(FilterJsonView.class)
	@GetMapping("/user-order-history")
	public ResponseEntity<?>  displayUserOrderHistory(){
		
		String email =  UserLoginInfo.userGlobalEmail;// user email
		
		if(email != null) {		
			List <OrderHistory> orHistory = orHisService.getAllOrderHistory();
			return ResponseEntity.badRequest().body(orHistory);	
		}else {	    
			return new ResponseEntity<>(UnauthorizedUser.getMessage("/user-order-history"), HttpStatus.UNAUTHORIZED);
		}
	}	
}
