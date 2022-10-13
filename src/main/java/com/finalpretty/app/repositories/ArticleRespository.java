package com.finalpretty.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.finalpretty.app.model.Article;

public interface ArticleRespository extends JpaRepository< Article, Integer>{
	
	

}
