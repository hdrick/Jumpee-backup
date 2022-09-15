package com.dricks.jumpee.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wallet_account")
public class WalletAccountBalance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wallet_account_id")
	private int walletAccountId;
	private int userId;
	
	@Column(precision=10, scale=2)
	private BigDecimal balance;

	public WalletAccountBalance() {}
	
	public WalletAccountBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public WalletAccountBalance(int walletAccountId, int userId, BigDecimal balance) {
		this.walletAccountId = walletAccountId;
		this.userId = userId;
		this.balance = balance;
	}

	public int getWalletAccountId() {
		return walletAccountId;
	}

	public void setWalletAccountId(int walletAccountId) {
		this.walletAccountId = walletAccountId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "WalletAccountBalance [walletAccountId=" + walletAccountId + ", userId=" + userId + ", balance="
				+ balance + "]";
	}
}
