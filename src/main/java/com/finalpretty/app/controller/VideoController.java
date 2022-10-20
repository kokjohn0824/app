package com.finalpretty.app.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.finalpretty.app.model.Video;
import com.finalpretty.app.repositories.VideoRespository;

@Controller
public class VideoController {

	@Autowired
	private VideoRespository videoR;

	// 後台
	// 顯示全部影片
	@GetMapping("/video/manage")
	public String manageVideoPage() {
		return "/video/backEndManageVideo";
	}

	// 前往新增影片
	@GetMapping("/video/add")
	public String addArticle() {
		return "/video/backEndAddVideoPage";
	}

	@PostMapping("/video/add")
	@ResponseBody
	public String processAction(@RequestParam("myFiles") MultipartFile mf) throws IllegalStateException, IOException {

		String fileName = mf.getOriginalFilename();
		System.out.println("fileName:" + fileName);
		String saveFileDir = "C:/fin2/fff2/src/main/resources/static/video";
		File saveFilePath = new File(saveFileDir, fileName);
		byte[] b = mf.getBytes();
		mf.transferTo(saveFilePath);
		if (fileName != null && fileName.length() != 0) {
			videoR.setUrl(fileName);
		}
		return "SaveFilePath:" + saveFilePath;
	}

	// =============================================================================================
	// 前台
	// 顯示影片分類
	@GetMapping("/video/categories")
	public String videoCategories() {
		return "/video/frontEndVideoCategories";
	}

	// 選擇分類
	@GetMapping("/video/type/{type}")
	public String videoCategoriesByType(@PathVariable String type, Model m) {
		List<Video> list = videoR.findByType(type);
		m.addAttribute("list", list);
		return "/video/frontEndVideoCategoriesByType";
	}

	// @GetMapping("/video/add")
	// public String addVideo(Model model) {

	// Video v1 = new Video();

	// model.addAttribute("article", v1);

	// // Messages lastestMsg = mService.findLastest();
	// // model.addAttribute("lastestMsg", lastestMsg);

	// List<Video> allVideo = videoR.findAll();

	// model.addAttribute("allVideo", allVideo);

	// return "/video/backEndAddVideoPage";
	// }

	@PostMapping("/video/post")
	public String postVideo(@ModelAttribute(name = "video") Video video, Model model) {

		videoR.save(video);

		Video v1 = new Video();

		model.addAttribute("video", v1);

		List<Video> allVideo = videoR.findAll();

		model.addAttribute("allVideo", allVideo);

		return "video/addVideoPage";
	}

	// @GetMapping("/article/page")
	// public String showMessages(@RequestParam(name = "p", defaultValue = "1")
	// Integer pageNumber, Model model) {
	// Page<Article> page = articleR.findByPage(pageNumber);
	// model.addAttribute("page", page);
	//
	// return "messages/showArticle";
	// }

	@GetMapping("/video/delete")
	public String deleteVideo(@RequestParam(name = "id") Integer id) {
		videoR.deleteById(id);

		return "redirect:/video/page";
	}

	@GetMapping("/video/edit")
	public String editVideo(@RequestParam(name = "id") Integer id, Model model) {
		Optional<Video> v1 = videoR.findById(id);
		model.addAttribute("video", v1.orElse(null));

		return "video/editVideo";
	}

	@PostMapping("/video/edit")
	public String editVideoPost(@ModelAttribute(name = "video") Video video) {
		videoR.save(video);

		return "redirect:/video/page";
	}

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

	@GetMapping("/video/ajax")
	public String getAjaxPage() {
		return "video/ajax-video";
	}

}
