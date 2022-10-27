package com.finalpretty.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.finalpretty.app.Response.ProductDto;
import com.finalpretty.app.model.Product;

public interface ProductRespository extends JpaRepository<Product, Integer> {

	@Transactional
	@Modifying
	@Query(value = "update product set onsale= :onsale where product_id = :product_id", nativeQuery = true)
	void updateOnsale(@Param("onsale") Integer onsale, @Param("product_id") Integer product_id);

	@Transactional
	@Modifying
	@Query(value = "update product set title = :title, price = :price,"
			+ "stock = :stock, type = :type, onsale= :onsale, text = :text, picture = :picture"
			+ " where product_id = :product_id", nativeQuery = true)
	void updatedProduct(@Param("title") String title, @Param("price") Integer price, @Param("stock") Integer stock,
			@Param("type") String type, @Param("onsale") Integer onsale, @Param("text") String text,
			@Param("picture") byte[] picture, @Param("product_id") Integer product_id);

	@Transactional
	@Modifying
	@Query(value = "delete from product where product_id = :product_id", nativeQuery = true)
	void deleteProduct(@Param("product_id") Integer product_id);

	@Query(value = "select * from product order by product_id desc", nativeQuery = true)
	List<Product> findAll();

	@Query(value = "select * from product where title like :productname or [text] like :productname order by product_id desc", nativeQuery = true)
	List<Product> selectLike(@Param("productname") String productname);

}
