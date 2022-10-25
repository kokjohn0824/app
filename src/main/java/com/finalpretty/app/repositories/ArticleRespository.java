package com.finalpretty.app.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.finalpretty.app.model.Article;

public interface ArticleRespository extends JpaRepository<Article, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update article set title=:title, text=:text, picture=:picture where article_id=:article_id", nativeQuery = true)
    void updateById(@Param("article_id") Integer article_id,
            @Param("title") String title,
            @Param("text") String text,
            @Param("picture") byte[] picture);

    @Transactional
    @Modifying
    @Query(value = "select * from article order by article_id desc", nativeQuery = true)
    List<Article> findAlloOrderById();

}
