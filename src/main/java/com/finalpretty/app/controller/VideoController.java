package com.finalpretty.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	public String manageVideoPage(Model m) {
		List<Video> list = videoR.findAll();
		m.addAttribute("list", list);
		return "/video/backEndManageVideo";
	}

	// 前往新增影片
	@GetMapping("/video/add")
	public String addArticle() {
		return "/video/backEndAddVideoPage";
	}

	// 新增影片
	@PostMapping("/video/add")
	@ResponseBody
	public String processAction(@RequestParam("myFiles") MultipartFile mf,
			@RequestParam(name = "picture") MultipartFile picture,
			@RequestParam(name = "title") String title,
			@RequestParam(name = "type") String type,
			@RequestParam(name = "body_parts") String body_parts) throws IllegalStateException, IOException {

		String fileName = mf.getOriginalFilename();
		// System.out.println("fileName:" + fileName);
		String saveFileDir = "C:/fin2/fff2/src/main/resources/static/video";
		File saveFilePath = new File(saveFileDir, fileName);
		// byte[] b = mf.getBytes();
		mf.transferTo(saveFilePath);
		Video video = new Video();
		video.setPicture(picture.getBytes());
		video.setTitle(title);
		video.setType(type);
		video.setBody_parts(body_parts);
		video.setUrl(fileName);
		videoR.save(video);
		// if (fileName != null && fileName.length() != 0) {
		// videoR.setUrl(fileName);
		// }
		// return "SaveFilePath:" + saveFilePath;
		// return null;
		return "/video/backEndAddVideoPage";
	}

	// 刪除影片(by id)
	@GetMapping("/video/delete")
	public String deleteVideo(@RequestParam(name = "video_id") Integer video_id) {
		videoR.deleteById(video_id);
		return "redirect:/video/manage";
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

	// 顯示分類影片的預覽圖
	@GetMapping("/showVideoImage/{id}")
	public ResponseEntity<byte[]> showVideoImage(@PathVariable Integer id) {
		System.out.println();
		Video video = videoR.findById(id).get();
		byte[] photoFile = video.getPicture();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(photoFile, headers, HttpStatus.OK);
	}

	// 顯示選取影片
	@GetMapping("/video/show")
	public String showVideo(@RequestParam(name = "video_id") Integer video_id, Model m) {
		Optional<Video> optional = videoR.findById(video_id);
		Video video = optional.get();
		m.addAttribute("video", video);
		return "/video/frontEndShowVideo";
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
