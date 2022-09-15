package com.dricks.jumpee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dricks.jumpee.entity.WalletAccountBalance;

@Repository
public interface WalletAccountBalanceDao extends JpaRepository<WalletAccountBalance, Integer>{
	
	WalletAccountBalance findByUserId (int id);
}
