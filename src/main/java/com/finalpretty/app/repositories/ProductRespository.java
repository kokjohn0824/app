package com.finalpretty.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalpretty.app.model.Product;

public interface ProductRespository  extends JpaRepository< Product, Integer>{
	
}
