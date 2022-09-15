package com.dricks.jumpee.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="prod_id")
	private int prodId;
	@Column(name="prod_name")
	private String prodName;
	@Column(name="prod_desc")
	private String prodDesc;
	@Column(name="prod_price")
	private BigDecimal prodPrice;
	@Column(name="prod_stocks")
	private int stocks;
	
	public Product() {}

	public Product(int prodId, String prodName, String prodDesc, BigDecimal prodPrice, int stocks) {
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodDesc = prodDesc;
		this.prodPrice = prodPrice;
		this.stocks = stocks;
	}

	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdDesc() {
		return prodDesc;
	}

	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}

	public BigDecimal getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(BigDecimal prodPrice) {
		this.prodPrice = prodPrice;
	}	

	public int getStocks() {
		return stocks;
	}

	public void setStocks(int stocks) {
		this.stocks = stocks;
	}

	@Override
	public String toString() {
		return "Product [prodId=" + prodId + ", prodName=" + prodName + ", prodDesc=" + prodDesc + ", prodPrice="
				+ prodPrice + ", stocks=" + stocks + "]";
	}
	
}
