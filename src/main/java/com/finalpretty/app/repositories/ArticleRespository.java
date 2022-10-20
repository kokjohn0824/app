package com.finalpretty.app.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import com.finalpretty.app.model.Article;

public interface ArticleRespository extends JpaRepository< Article, Integer>{
	
    @Transactional
	@Modifying
	@Query(value = "update article set title=:title, text=:text, picture=:picture where article_id=:article_id",
    nativeQuery = true)
	void updateById(@Param("article_id") Integer article_id,
                    @Param("title") String title,
                    @Param("text") String text,
                    @Param("picture") byte[] picture);


}
