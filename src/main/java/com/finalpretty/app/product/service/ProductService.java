package com.finalpretty.app.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finalpretty.app.Response.ProductDto;
import com.finalpretty.app.model.Product;
import com.finalpretty.app.repositories.ProductRespository;

@Service
// @Transactional
public class ProductService {

	@Autowired
	private ProductRespository pDao;

	public List<Product> selectByEatProduct() {
		return pDao.selectByEatProduct();
	}

	public List<Product> selectByUseProduct() {
		return pDao.selectByUseProduct();
	}

	public List<Product> findAllByOnSale() {
		return pDao.findAllByOnSale();
	}

	public List<Product> findAll() {
		return pDao.findAll();
	}

	@Transactional
	public void insert(Product product) {
		pDao.save(product);
	}

	@Transactional
	public Product findById(Integer id) {
		Optional<Product> op = pDao.findById(id);
		if (op.isPresent()) {
			return op.get();
		}
		return null;
	}

	@Transactional
	public void updateOnsale(Integer onsale, Integer id) {
		pDao.updateOnsale(onsale, id);
		// System.out.println(findById(id).getOnsale());
	}

	@Transactional
	public void updateProduct(String title, Integer price, Integer stock, String type,
			Integer onsale, String text, byte[] picture, Integer product_id) {
		pDao.updatedProduct(title, price, stock, type, onsale, text, picture, product_id);
	}

	@Transactional
	public void deleteProduct(Integer product_id) {
		pDao.deleteProduct(product_id);
	}

	public List<Product> selectLike(String prodcutname) {
		if (("\"\"").equals(prodcutname)) {
			return pDao.selectLike("%%");
		} else {
			return pDao.selectLike("%" + prodcutname + "%");
		}
	}

	public List<Product> fuzzySearch(String word) {
		List<Product> list = pDao.fuzzySearch("%" + word + "%");
		if (list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}

}
