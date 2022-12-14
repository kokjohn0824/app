package com.finalpretty.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.finalpretty.app.model.Article;

public interface ArticleRespository extends JpaRepository<Article, Integer> {

        @Transactional
        @Modifying
        @Query(value = "update article set title=:title, text=:text,type=:type, picture=:picture where article_id=:article_id", nativeQuery = true)
        void updateById(@Param("article_id") Integer article_id,
                        @Param("title") String title,
                        @Param("type") String type,
                        @Param("text") String text,
                        @Param("picture") byte[] picture);

        @Transactional
        @Modifying
        @Query(value = "select * from article order by article_id desc", nativeQuery = true)
        List<Article> findAlloOrderById();

        @Query(value = "select * from article where title like :searchinput or [text] like :searchinput", nativeQuery = true)
        List<Article> selectLike(String searchinput);

        @Query(value = "select * from article where "
                        + "title like :likeTest "
                        + "or type like :likeTest "
                        + "or convert(nvarchar,added, 112) like :likeTest "
                        + "or convert(nvarchar,added, 102) like :likeTest "
                        + "or convert(nvarchar,added, 111) like :likeTest "
                        + "or text like :likeTest order by article_id desc", nativeQuery = true)
        List<Article> fuzzySerch(@Param("likeTest") String likeTest);

        @Query(value = "select * from article order by views desc", nativeQuery = true)
        List<Article> hotSerch();

        @Query(value = "select * from article where type=:type order by article_id desc", nativeQuery = true)
        List<Article> typeSerch(@Param("type") String type);
}
