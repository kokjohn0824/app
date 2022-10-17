package com.finalpretty.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.finalpretty.app.model.Article;

public interface ArticleRespository extends JpaRepository< Article, Integer>{
	
//    @Query(value = "select name,author,price from Book b where b.price>?1 and b.price<?2")
//    List<Book> findByPriceRange(long price1, long price1);
	

}
