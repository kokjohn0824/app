package com.finalpretty.app.product.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.finalpretty.app.Response.ArticleResponse;
import com.finalpretty.app.model.Article;
import com.finalpretty.app.repositories.ArticleRespository;

@Service
@Transactional
public class ArticleService {

	@Autowired
	private ArticleRespository articleR;

	public Page<Article> findByPage(Integer pageNumber) {
		Pageable pgb = PageRequest.of(pageNumber - 1, 4, Sort.Direction.DESC, "added");
		Page<Article> page = articleR.findAll(pgb);
		// Page<ArticleResponse> pageDto;
		// ArticleResponse aDto;
		// for(Article p : page){
		// aDto = new ArticleResponse();
		// aDto.setArticle_id(p.getArticle_id());
		// aDto.setTitle(p.getTitle());
		// aDto.setText(p.getText());
		// }
		return page;
	}
}
