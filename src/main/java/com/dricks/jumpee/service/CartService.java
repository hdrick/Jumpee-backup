package com.dricks.jumpee.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dricks.jumpee.dao.CartDao;
import com.dricks.jumpee.dao.ProductDao;
import com.dricks.jumpee.entity.Cart;
import com.dricks.jumpee.entity.Checkout;
import com.dricks.jumpee.entity.Product;
import com.dricks.jumpee.entity.User;
import com.dricks.jumpee.entity.WalletAccountBalance;
import com.dricks.jumpee.helpers.UserLoginInfo;
import com.dricks.jumpee.response.MessageResponse;

@Service
public class CartService {

	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private ProductDao prodDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WalletAccountService walletAccBal;
		
	@Autowired
	OrderHistoryService orHistorySerice;
	
	public ResponseEntity<?> addToCart(Cart theCart) {
		Product prod = prodDao.findByProdName(theCart.getProductName());
		User theUser = userService.getUserDetails(UserLoginInfo.userGlobalEmail);
		if(Objects.nonNull(prod)) {
			
			int userId = theUser.getUser_id();// user ID
			int prodId = prod.getProdId();// product ID
			String prodName = theCart.getProductName();// prod order name 
			int qty = theCart.getQty();// quantity of prod to order	
			BigDecimal price = prod.getProdPrice(); // price of prod.			
			BigDecimal total  = price.multiply(BigDecimal.valueOf(qty)); // total cost				
			String status = "PENDING";// status of order
			
			theCart.setProdId(prodId);
			theCart.setUserId(userId);
			theCart.setProductName(prodName);
			theCart.setQty(qty);
			theCart.setProductPrice(price);
			theCart.setTotal(total);
			theCart.setStatus(status);
			theCart.setOrderNumber(generateOrNumber());
			
			cartDao.save(theCart);
			
			return new ResponseEntity<>(theCart, HttpStatus.OK);
		}else {
			return null;
		}
	}
	
	public String generateOrNumber() {	
		ThreadLocalRandom random = ThreadLocalRandom.current();
		long num = random.nextLong(10_000_000_0L, 100_000_000_0L);	  
		String orderNum = "OR" + num;	  
		return orderNum;
	}
	
	public ResponseEntity<?> checkOut(Checkout theOrderNum){
		//get the details of orderNumber
		Cart cart = cartDao.findCartByOrderNumber(theOrderNum.getOrderNumber());
		String prodDesc = getCartProdDesc(cart.getProductName());//get product description
		
		BigDecimal userBal = getUserMoney(cart.getUserId());
		
		BigDecimal amtToPay = cart.getTotal();
				
		BigDecimal change = userBal.subtract(amtToPay);	
		float newChange = change.floatValue();
		
		if(newChange<0) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Transaction cannot succeed due to"
					+ " INSUFFICIENT Balance."));
		}else {
			walletAccBal.updateUserbalance(cart.getUserId(), change);//update user balance 
			orHistorySerice.addOrHistory(cart, prodDesc);//add to order history
			updateStocks(cart.getProductName(), cart.getQty());// to update stocks
			return ResponseEntity.ok().body(new MessageResponse("Success: Transaction success your new Blance is: "
					+ " Php "+change));
		}	
	}
	
	public BigDecimal getUserMoney(int userId) {
		WalletAccountBalance balance = walletAccBal.getUserWalletAccountDetails(userId);		
		BigDecimal bal = balance.getBalance();
		return bal;
	}
	
	
	//get product description
	public String getCartProdDesc(String prodName) {
		Product prod = prodDao.findByProdName(prodName);
		return prod.getProdDesc();
	}
	
	public void updateStocks(String prodName, int qty) {
		Product prod = prodDao.findByProdName(prodName);

		int prodStocks = prod.getStocks();	
		int newStocks = prodStocks-qty;
		
		prod.setStocks(newStocks);
		prodDao.save(prod);
	}
	
	public List <Cart> getUserCart(){
		int userId = UserLoginInfo.userGlobalId;
		
		List <Cart> theCart = cartDao.findCartAll(userId);
		
		return theCart;
	}
	
}
