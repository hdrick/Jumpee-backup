package com.dricks.jumpee.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dricks.jumpee.dao.UserDao;
import com.dricks.jumpee.dao.WalletAccountBalanceDao;
import com.dricks.jumpee.dao.WalletTransactionDao;
import com.dricks.jumpee.entity.User;
import com.dricks.jumpee.entity.WalletAccountBalance;
import com.dricks.jumpee.entity.WalletTransaction;
import com.dricks.jumpee.helpers.UserLoginInfo;

@Service
public class WalletAccountService {

	@Autowired
	private WalletAccountBalanceDao walletAccountDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WalletTransactionDao walletTransDao;
	
	public void addBalance(WalletAccountBalance walletAccBal) {
		int userId = this.getWalletAccountUserId(UserLoginInfo.userGlobalEmail);
		
		walletAccBal.setUserId(userId);
		walletAccountDao.save(walletAccBal);
		addDepositDate(walletAccBal.getBalance());
	}
	
	public WalletAccountBalance deposit(WalletAccountBalance walletAccBal) {
		int userId = this.getWalletAccountUserId(UserLoginInfo.userGlobalEmail);
		WalletAccountBalance walletDetails = this.getUserWalletAccountDetails(userId);
		
		//get wallet amount in db
		BigDecimal balanceInAccount = walletDetails.getBalance();
		//get amount to add
		BigDecimal amountToAdd = walletAccBal.getBalance();
		//total amount
		BigDecimal result = balanceInAccount.add(amountToAdd);
		
		walletDetails.setBalance(result);
	
		addDepositDate(amountToAdd);
		return walletAccountDao.save(walletDetails);
	}
	
	public int getWalletAccountUserId(String email) {
		User user = userDao.findByEmail(email);
		return user.getUser_id();
	}
	
	public WalletAccountBalance getUserWalletAccountDetails(int id) {
		return walletAccountDao.findByUserId(id);
	}
	
	
	
	public void addDepositDate(BigDecimal deposit) {
		String email = UserLoginInfo.userGlobalEmail; //current email of user
		int userId=0;// current id of user.
		
		WalletTransaction walletTran = new WalletTransaction();
		
		User userEmail = userService.getUserDetails(email);
		userId = userEmail.getUser_id();

		walletTran.setDepositDate(LocalDateTime.now());
		walletTran.setUserId(userId);
		walletTran.setDepositMoney(deposit);
		
		walletTransDao.save(walletTran);
	}
	
	//update user balance after transaction
	public void updateUserbalance(int userId, BigDecimal userNewBalance) {
		
		WalletAccountBalance userBal =  walletAccountDao.findByUserId(userId);
		
		userBal.setBalance(userNewBalance);
		walletAccountDao.save(userBal);
	}
	
	public WalletAccountBalance displayUserBalance() {
		WalletAccountBalance userWallDetails = 
				this.getUserWalletAccountDetails(UserLoginInfo.userGlobalId);
		return userWallDetails;
	}
	
	
	public List <WalletTransaction> getWalletHistoryTransac() {
		List <WalletTransaction> wallTransac = walletTransDao
				.findByUserId(UserLoginInfo.userGlobalId);
		
		return wallTransac;
	}
	
}
