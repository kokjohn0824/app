package com.finalpretty.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalpretty.app.model.Article;
import com.finalpretty.app.repositories.ArticleRespository;

@Controller
public class ArticleController {

	@Autowired
	private ArticleRespository articleR;

	@GetMapping("/article/manage")
	public String manageArticlePage() {
		return "manageArticle";
	}

	@GetMapping("/article/categories")
    public String videoCategories() {
        return "articleCategories";
    }

	@GetMapping("/article/add")
	public String addArticle(Model model) {

		Article a1 = new Article();

		model.addAttribute("article", a1);

		// Messages lastestMsg = mService.findLastest();
		// model.addAttribute("lastestMsg", lastestMsg);

		List<Article> allArticle = articleR.findAll();

		model.addAttribute("allArticle", allArticle);

		return "addArticlePage";
	}

	@PostMapping("/article/post")
	public String postMsg(@ModelAttribute(name = "article") Article article, Model model) {

		articleR.save(article);

		Article a1 = new Article();

		model.addAttribute("article", a1);

		List<Article> allArticle = articleR.findAll();

		model.addAttribute("allArticle", allArticle);

		return "addArticlePage";
	}

//	@GetMapping("/article/page")
//	public String showMessages(@RequestParam(name = "p", defaultValue = "1") Integer pageNumber, Model model) {
//		Page<Article> page = articleR.findByPage(pageNumber);
//		model.addAttribute("page", page);
//
//		return "messages/showArticle";
//	}

	@GetMapping("/article/delete")
	public String deleteArticle(@RequestParam(name = "id") Integer id) {
		articleR.deleteById(id);

		return "redirect:/article/page";
	}

	@GetMapping("/article/edit")
	public String editArticle(@RequestParam(name = "id") Integer id, Model model) {
		Optional<Article> a1 = articleR.findById(id);
		model.addAttribute("article", a1.orElse(null));

		return "article/editArticle";
	}

	@PostMapping("/article/edit")
	public String editMessagePost(@ModelAttribute(name = "article") Article article) {
		articleR.save(article);

		return "redirect:/article/page";
	}

//	@ResponseBody
//	@PostMapping("/article/api/post")
//	public List<Article> postMessagsApi(@RequestBody ArticleRespository articleR) {
//		String userInput = articleR.getInputText();
//
//		// ...
//
//		Messages newMsg = new Messages();
//		newMsg.setText(userInput);
//
//		articleR.insert(newMsg);
//
//		Page<Article> page = articleR.findByPage(1);
//		return page.getContent();
//	}

	@GetMapping("/article/ajax")
	public String getAjaxPage() {
		return "article/ajax-article";
	}

}
