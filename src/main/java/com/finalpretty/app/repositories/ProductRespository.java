package com.finalpretty.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.finalpretty.app.model.Product;


public interface ProductRespository  extends JpaRepository<Product, Integer>{
	
	@Transactional
	@Modifying
	@Query(value="update product set onsale= :onsale where product_id = :product_id",
		nativeQuery = true)
	void updateOnsale(@Param("onsale")Integer onsale, @Param("product_id")Integer product_id);
	
}
