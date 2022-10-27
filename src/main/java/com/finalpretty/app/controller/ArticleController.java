package com.finalpretty.app.controller;

import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Set;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.finalpretty.app.Response.ArticleResponse;
import com.finalpretty.app.model.Article;
import com.finalpretty.app.model.Member;
import com.finalpretty.app.model.Users;
import com.finalpretty.app.repositories.ArticleRespository;
import com.finalpretty.app.repositories.MemberRespository;

@Controller
public class ArticleController {

	@Autowired
	private ArticleRespository articleR;

	@Autowired
	private MemberRespository memberR;

	// 後台
	// 顯示全部文章
	@GetMapping("/article/manage")
	public String manageArticlePage(Model m) {
		List<Article> list = articleR.findAlloOrderById();
		m.addAttribute("list", list);
		return "/article/backEndManageArticle";
	}

	// 顯示全部文章的圖片
	@GetMapping("/public/showArticleImage/{id}")
	public ResponseEntity<byte[]> showArticleImage(@PathVariable Integer id) {
		Article article = articleR.findById(id).get();
		byte[] photoFile = article.getPicture();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(photoFile, headers, HttpStatus.OK);
	}

	// 前往新增文章
	@GetMapping("/article/add")
	public String goAddArticle() {
		return "/article/backEndAddArticlePage";
	}

	// 刪除文章(by id)
	@GetMapping("/article/delete")
	public String deleteArticle(@RequestParam(name = "article_id") Integer article_id) {
		articleR.deleteById(article_id);
		return "redirect:/article/manage";
	}

	// 新增文章
	@PostMapping("/article/add")
	public String addArticle(
			@RequestParam(name = "title") String title,
			@RequestParam(name = "text") String text,
			@RequestParam(name = "file") MultipartFile file) {
		Article article = new Article();
		try {
			article.setTitle(title);
			article.setText(text);
			article.setPicture(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		articleR.save(article);
		return "/article/backEndAddArticlePage";
	}

	// 修改文章
	@GetMapping("/article/edit")
	public String editArticle(@RequestParam(name = "article_id") Integer id, Model model) {
		Optional<Article> a1 = articleR.findById(id);
		model.addAttribute("article", a1.orElse(null));
		return "/article/backEndEditArticle";
	}

	@PostMapping("/article/edit")
	public String editArticlePost(@RequestParam(name = "article_id") Integer article_id,
			@RequestParam(name = "title") String title,
			@RequestParam(name = "text") String text,
			@RequestParam(name = "picture") MultipartFile picture,
			Model model) {
		Article article = new Article();
		try {
			byte[] picture2 = picture.getBytes();
			article.setPicture(picture2);
			article.setTitle(title);
			article.setText(text);
			articleR.updateById(article_id, title, text, picture2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "/article/backEndManageArticle";
	}

	// =============================================================================================
	// 前台
	// 顯示全部文章
	@GetMapping("/public/article/categories")
	public String articleCategories(Model m) {
		List<Article> list = articleR.findAlloOrderById();
		m.addAttribute("list", list);
		return "/article/frontEndArticleCategories";
	}

	// 顯示選取文章
	@GetMapping("/public/article/show")
	public String showArticle(@RequestParam(name = "article_id") Integer article_id, Model m) {
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Member member;
		Optional<Article> optional = articleR.findById(article_id);
		Article article = optional.get();
		Set<Member> members = article.getMembers();
		if (o instanceof Users) {
			member = ((Users) o).getFkMember();
			if (members.contains(member)) {
				m.addAttribute("bool", "true");
			} else {
				m.addAttribute("bool", "false");
			}

		}
		m.addAttribute("article", article);

		return "/article/frontEndShowArticle";
	}

	// 按讚文章
	@ResponseBody
	@PostMapping("/article/like/{article_id}")
	public ArticleResponse likeArticle(
			@PathVariable(name = "article_id") Integer article_id) {
		try {
			Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Member member = ((Users) o).getFkMember();
			Integer member_id = member.getMember_id();
			Member memberFromJpa = memberR.findById(member_id).get();
			Article article = articleR.findById(article_id).get();
			Set<Article> like = memberFromJpa.getArticles();
			like.add(article);
			memberR.save(member);

			ArticleResponse ar = new ArticleResponse();
			ar.setArticle_id(article_id);
			ar.setMember_id(member_id);

			return ar;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 取消按讚文章
	@ResponseBody
	@PostMapping("/article/delike/{article_id}")
	public ArticleResponse delikeArticle(
			@PathVariable(name = "article_id") Integer article_id) {
		try {
			Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Member member = ((Users) o).getFkMember();

			Integer member_id = member.getMember_id();
			Member memberFromJpa = memberR.findById(member_id).get();
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println(member);
			System.out.println(memberFromJpa);
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			Article article = articleR.findById(article_id).get();
			Set<Article> ss = memberFromJpa.getArticles();
			ss.remove(article);
			ArticleResponse ar = new ArticleResponse();
			ar.setArticle_id(article_id);
			ar.setMember_id(member_id);
			articleR.save(article);

			return ar;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 取消按讚文章
	// @ResponseBody
	// @GetMapping("/public/article/findlike/{member_id}/{article_id}")
	// public boolean findLikeArticle(
	// @PathVariable(name = "article_id") Integer article_id,
	// @PathVariable(name = "member_id") Integer member_id) {
	// System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
	// System.out.println(article_id);
	// System.out.println(member_id);
	// System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
	// Member member = memberR.findById(member_id).get();
	// Set<Article> ss = member.getArticles();
	// if (ss != null) {
	// return true;
	// } else {
	// return false;
	// }
	// }

	// =============================================================================================

	// @PostMapping("/article/post")
	// public String postMsg(@ModelAttribute(name = "article") Article article,
	// Model model) {

	// articleR.save(article);

	// Article a1 = new Article();

	// model.addAttribute("article", a1);

	// List<Article> allArticle = articleR.findAll();

	// model.addAttribute("allArticle", allArticle);

	// return "addArticlePage";
	// }

	// @GetMapping("/article/page")
	// public String showMessages(@RequestParam(name = "p", defaultValue = "1")
	// Integer pageNumber, Model model) {
	// Page<Article> page = articleR.findByPage(pageNumber);
	// model.addAttribute("page", page);
	//
	// return "messages/showArticle";
	// }

	// @ResponseBody
	// @PostMapping("/article/api/post")
	// public List<Article> postMessagsApi(@RequestBody ArticleRespository articleR)
	// {
	// String userInput = articleR.getInputText();
	//
	// // ...
	//
	// Messages newMsg = new Messages();
	// newMsg.setText(userInput);
	//
	// articleR.insert(newMsg);
	//
	// Page<Article> page = articleR.findByPage(1);
	// return page.getContent();
	// }

	// @GetMapping("/article/ajax")
	// public String getAjaxPage() {
	// return "article/ajax-article";
	// }

}
