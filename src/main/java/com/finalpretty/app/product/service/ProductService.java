package com.finalpretty.app.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finalpretty.app.model.Product;
import com.finalpretty.app.repositories.ProductRespository;

@Service
@Transactional
public class ProductService {
	
	@Autowired
	private ProductRespository pDao;
	
	public List<Product> findAll(){
		return pDao.findAll();
	}
	
	public void insert(Product product) {
		pDao.save(product);
	}
	
	public Product findById(Integer id) {
		Optional<Product> op = pDao.findById(id);
		if(op.isPresent()) {
			return op.get();
		}
		return null;
	}
	
	public void updateOnsale(Integer onsale, Integer id) {
		pDao.updateOnsale(onsale, id);
	}
	
}
