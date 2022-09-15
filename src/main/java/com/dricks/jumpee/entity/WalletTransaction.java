package com.dricks.jumpee.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dricks.jumpee.helpers.FilterJsonView;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="wallet_history")
public class WalletTransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int walletHistoryId;
	@JsonView(FilterJsonView.class)
	private int userId;
	@JsonView(FilterJsonView.class)
	private BigDecimal depositMoney;
	@JsonView(FilterJsonView.class)
	private LocalDateTime depositDate;
	
	public WalletTransaction() {}
	
	public WalletTransaction(int walletHistoryId, int userId, BigDecimal depositMoney, LocalDateTime depositDate) {
		this.walletHistoryId = walletHistoryId;
		this.userId = userId;
		this.depositMoney = depositMoney;
		this.depositDate = depositDate;
	}

	public int getWalletHistoryId() {
		return walletHistoryId;
	}

	public void setWalletHistoryId(int walletHistoryId) {
		this.walletHistoryId = walletHistoryId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public BigDecimal getDepositMoney() {
		return depositMoney;
	}

	public void setDepositMoney(BigDecimal depositMoney) {
		this.depositMoney = depositMoney;
	}

	public LocalDateTime getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(LocalDateTime depositDate) {
		this.depositDate = depositDate;
	}

	@Override
	public String toString() {
		return "WalletTransaction [walletHistoryId=" + walletHistoryId + ", userId=" + userId + ", depositMoney="
				+ depositMoney + ", depositDate=" + depositDate + "]";
	}
}
