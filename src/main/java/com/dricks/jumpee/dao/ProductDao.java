package com.dricks.jumpee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dricks.jumpee.entity.Product;

//@RepositoryRestResource(path="products")
@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

	Product findByProdName(String productName);

}
