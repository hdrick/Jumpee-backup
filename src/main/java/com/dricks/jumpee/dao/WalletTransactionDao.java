package com.dricks.jumpee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dricks.jumpee.entity.WalletTransaction;

@Repository
public interface WalletTransactionDao extends JpaRepository<WalletTransaction, Integer>{
	List <WalletTransaction> findByUserId(int id);
}
