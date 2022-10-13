package com.finalpretty.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalpretty.app.model.Video;
import com.finalpretty.app.repositories.VideoRespository;

@Controller
public class VideoController {

	@Autowired
	private VideoRespository videoR;

	@GetMapping("/video/add")
	public String Video(Model model) {

		Video v1 = new Video();

		model.addAttribute("video", v1);

		// Messages lastestMsg = mService.findLastest();
		// model.addAttribute("lastestMsg", lastestMsg);

		List<Video> allVideo = videoR.findAll();

		model.addAttribute("allVideo", allVideo);

		return "video/addVideoPage";
	}

//	@PostMapping("/article/post")
//	public String postMsg(@ModelAttribute(name = "article") Article article, Model model) {
//
//		videoR.save(article);
//
//		Article a1 = new Article();
//
//		model.addAttribute("article", a1);
//
//		List<Article> allArticle = videoR.findAll();
//
//		model.addAttribute("allArticle", allArticle);
//
//		return "article/addArticlePage";
//	}

//	@GetMapping("/article/page")
//	public String showMessages(@RequestParam(name = "p", defaultValue = "1") Integer pageNumber, Model model) {
//		Page<Article> page = videoR.findByPage(pageNumber);
//		model.addAttribute("page", page);
//
//		return "messages/showArticle";
//	}

	@GetMapping("/article/delete")
	public String deleteArticle(@RequestParam(name = "id") Integer id) {
		videoR.deleteById(id);

		return "redirect:/article/page";
	}

//	@GetMapping("/article/edit")
//	public String editMessage(@RequestParam(name = "id") Integer id, Model model) {
//		Optional<Article> a1 = videoR.findById(id);
//		model.addAttribute("messages", a1.orElse(null));
//
//		return "article/editMessage";
//	}
//
//	@PostMapping("/article/edit")
//	public String editMessagePost(@ModelAttribute(name = "messages") Article article) {
//		videoR.save(article);
//
//		return "redirect:/article/page";
//	}

//	@ResponseBody
//	@PostMapping("/article/api/post")
//	public List<Article> postMessagsApi(@RequestBody videoRespository videoR) {
//		String userInput = videoR.getInputText();
//
//		// ...
//
//		Messages newMsg = new Messages();
//		newMsg.setText(userInput);
//
//		videoR.insert(newMsg);
//
//		Page<Article> page = videoR.findByPage(1);
//		return page.getContent();
//	}

	@GetMapping("/article/ajax")
	public String getAjaxPage() {
		return "article/ajax-article";
	}

}
