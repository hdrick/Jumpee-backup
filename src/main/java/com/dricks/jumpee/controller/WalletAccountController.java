package com.dricks.jumpee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dricks.jumpee.entity.WalletAccountBalance;
import com.dricks.jumpee.entity.WalletTransaction;
import com.dricks.jumpee.helpers.FilterJsonView;
import com.dricks.jumpee.helpers.UnauthorizedUser;
import com.dricks.jumpee.helpers.UserLoginInfo;
import com.dricks.jumpee.response.MessageResponse;
import com.dricks.jumpee.service.WalletAccountService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class WalletAccountController {
	
	@Autowired
	private WalletAccountService walletAccService;
	
	
	@PostMapping("/account-first-deposit")
	public ResponseEntity<?> deposit(@RequestBody WalletAccountBalance walletAccBal){
		String email = UserLoginInfo.userGlobalEmail; //current email of user
		
		if(email !=null) {
			walletAccService.addBalance(walletAccBal);
			int userId = walletAccService.getWalletAccountUserId(UserLoginInfo.userGlobalEmail);
			//get new total balance and display to postman
			WalletAccountBalance newTotal = walletAccService.getUserWalletAccountDetails(userId);
			return ResponseEntity.ok().body(new MessageResponse("Success: Your account wallet has been successfully "
											+ "activated with an amount of Php "+newTotal.getBalance()));
		}else {

			return new ResponseEntity<>(UnauthorizedUser.getMessage("/account-first-deposit"), HttpStatus.UNAUTHORIZED);
		}	
	}
	
	@PutMapping("/deposit-to-wallet")
	public ResponseEntity<?> depositToAccountWallet(@RequestBody WalletAccountBalance amntToAdd){
		String email = UserLoginInfo.userGlobalEmail; //current email of user
			
		if(email !=null) {
			int userId = walletAccService.getWalletAccountUserId(UserLoginInfo.userGlobalEmail);
			walletAccService.deposit(amntToAdd);
			
			//get new total balance and display to postman
			WalletAccountBalance newTotal = walletAccService.getUserWalletAccountDetails(userId);
			
			return new ResponseEntity<>(new MessageResponse("Success: You have successfully deposit "+amntToAdd.getBalance()
															+" to your account. Your new balanace is: Php "+newTotal.getBalance()), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(UnauthorizedUser.getMessage("/deposit-to-wallet"), HttpStatus.UNAUTHORIZED); 
		}	
	}
	
	@GetMapping("/wallet-balance")
	public ResponseEntity<?> displayUserBalance(){
		String email = UserLoginInfo.userGlobalEmail; //current email of user
		if(email !=null) {
			WalletAccountBalance userBalance= walletAccService.displayUserBalance();
			
			return ResponseEntity.ok().body(userBalance);
		}else {
			return new ResponseEntity<>(UnauthorizedUser.getMessage("/wallet-balance"), HttpStatus.UNAUTHORIZED); 
		}	
	}
	
	@JsonView(FilterJsonView.class)
	@GetMapping("/wallet-transaction")
	public ResponseEntity<?> displayUserWalletTransaction(){
		String email = UserLoginInfo.userGlobalEmail; //current email of user
		if(email !=null) {
			List <WalletTransaction> userWallTransac = walletAccService.getWalletHistoryTransac();
			return ResponseEntity.ok().body(userWallTransac);
		}else {
			return new ResponseEntity<>(UnauthorizedUser.getMessage("/wallet-transaction"), HttpStatus.UNAUTHORIZED); 
		}	
	}
	
}
