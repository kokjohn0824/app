package com.finalpretty.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finalpretty.app.model.Article;
import com.finalpretty.app.model.Product;
import com.finalpretty.app.product.service.ProductService;
import com.finalpretty.app.repositories.ArticleRespository;

@Controller
@RequestMapping("/public/api/nav")
public class NavRestController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ArticleRespository articleRespository;

    @PostMapping("/product")
    @ResponseBody
    public List<Product> selectProductLike(@RequestBody Map<String, String> searchInput) {
        return productService.selectLike(searchInput.get("searchInput"));
    }

    @PostMapping("/article")
    @ResponseBody
    public List<Article> selectArticleLike(@RequestBody Map<String, String> searchInput) {
        if (("\"\"").equals(searchInput.get("searchInput"))) {
            return articleRespository.selectLike("%%");
        } else {
            return articleRespository.selectLike("%" + searchInput.get("searchInput") + "%");
        }
    }

}
