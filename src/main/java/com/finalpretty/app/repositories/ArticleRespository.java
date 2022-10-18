package com.finalpretty.app.repositories;

import org.springframework.data.jpa.repository.*;

import com.finalpretty.app.model.Article;

public interface ArticleRespository extends JpaRepository< Article, Integer>{
	
}
