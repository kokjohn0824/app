package com.finalpretty.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.finalpretty.app.model.Member;
import com.finalpretty.app.model.Users;
import com.finalpretty.app.repositories.MemberRespository;
import com.finalpretty.app.security.UsersRepository;

@Controller
public class MemberManageController {

	@Autowired
	private MemberRespository memberR;

	@Autowired
	private UsersRepository userR;

	// ----------後台-------------------------------------------------------------

	// 後台主頁
	@GetMapping("/backendMember/page")
	public String backendMemberPage() {
		return "/member/backendMemberPage";
	}

	// 前往新增會員頁面
	@GetMapping("/backendMember/add")
	public String backendMemberAdd() {
		return "/member/backendMemberAdd";
	}

	// 新增會員
	@PostMapping("/backendMember/add")
	public String addMember(
			@RequestParam(name = "nickname", required = false) String nickname,
			@RequestParam(name = "gender", required = false) Integer gender,
			@RequestParam(name = "age", required = false) Integer age,
			@RequestParam(name = "height", required = false) Double height,
			@RequestParam(name = "weight", required = false) Double weight,
			@RequestParam(name = "bodyFat", required = false) Double bodyFat,
			@RequestParam(name = "visceralFat", required = false) Double visceralFat,
			@RequestParam(name = "muscleMass", required = false) Double muscleMass,
			@RequestParam(name = "becomeVIP", required = false) Integer becomeVIP,
			@RequestParam(name = "photo", required = false) MultipartFile photo) {
		Member member = new Member();
		try {
			member.setNickname(nickname);
			member.setGender(gender);
			member.setAge(age);
			member.setHeight(height);
			member.setWeight(weight);
			member.setBodyFat(bodyFat);
			member.setVisceralFat(visceralFat);
			member.setMuscleMass(muscleMass);
			member.setBecomeVIP(becomeVIP);
			member.setPhoto(photo.getBytes());
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		memberR.save(member);
		// return "redirect:/backendMember/showAll";
		return "/member/backendMemberAdd";
	}

	// 顯示全部會員
	@GetMapping("/backendMember/showAll")
	public String memberGetAll(Model m) {
		List<Member> list = memberR.findAll();
		m.addAttribute("list", list);
		return "/member/backendMemberShowAll";
	}

	// 顯示全部會員的圖片
	@GetMapping("/public/showMemberImage/{id}")
	public ResponseEntity<byte[]> showMemberImage(@PathVariable Integer id) {
		Member member = memberR.findById(id).get();
		byte[] photoFile = member.getPhoto();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(photoFile, headers, HttpStatus.OK);
	}

	// 刪除會員
	@GetMapping("/backendMember/delete")
	public String memberDelete(@RequestParam(name = "member_id") Integer member_id) {
		memberR.deleteById(member_id);
		return "redirect:/backendMember/showAll";
	}

	// 編輯會員資料
	@GetMapping("/backendMember/edit")
	public String backendMemberEdit(@RequestParam(name = "member_id") Integer id, Model m) {
		Optional<Member> m1 = memberR.findById(id);
		m.addAttribute("member", m1.orElse(null));
		return "/member/backendMemberEdit";
	}

	@PostMapping("/backendMember/edit")
	public String backendMemberEditPost(@RequestParam(name = "member_id") Integer member_id,
			@RequestParam(name = "nickname", required = false) String nickname,
			@RequestParam(name = "gender", required = false) Integer gender,
			@RequestParam(name = "age", required = false) Integer age,
			@RequestParam(name = "height", required = false) Double height,
			@RequestParam(name = "weight", required = false) Double weight,
			@RequestParam(name = "bodyFat", required = false) Double bodyFat,
			@RequestParam(name = "visceralFat", required = false) Double visceralFat,
			@RequestParam(name = "muscleMass", required = false) Double muscleMass,
			@RequestParam(name = "becomeVIP", required = false) Integer becomeVIP,
			Model m) {
		Member member = new Member();

		try {
			member.setNickname(nickname);
			member.setGender(gender);
			member.setAge(age);
			member.setHeight(height);
			member.setWeight(weight);
			member.setBodyFat(bodyFat);
			member.setVisceralFat(visceralFat);
			member.setMuscleMass(muscleMass);
			member.setBecomeVIP(becomeVIP);
			memberR.updateById(member_id, nickname, gender, age, height, weight, bodyFat,
					visceralFat, muscleMass, becomeVIP);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/backendMember/showAll";
	}

	// ----------前台-------------------------------------------------------------

	// 個人資料主頁
	// @GetMapping("/member/inputpage")
	// public String memberPage() {
	// return "/member/memberInputPage";
	// }

	// 註冊完，輸入會員資料
	@GetMapping("/member/inputpage")
	public String memberPage(Integer member_id) {
		return "/member/memberInputPage";
	}

	@PostMapping("/member/inputpage")
	public String memberInputPage(
			@RequestParam(name = "nickname", required = false) String nickname,
			@RequestParam(name = "gender", required = false) Integer gender,
			@RequestParam(name = "age", required = false) Integer age,
			@RequestParam(name = "height", required = false) Double height,
			@RequestParam(name = "weight", required = false) Double weight,
			@RequestParam(name = "bodyFat", required = false) Double bodyFat,
			@RequestParam(name = "visceralFat", required = false) Double visceralFat,
			@RequestParam(name = "muscleMass", required = false) Double muscleMass,
			@RequestParam(name = "becomeVIP", required = false) Integer becomeVIP,
			@RequestParam(name = "photo", required = false) MultipartFile photo) {
		Member member = new Member();
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer user_id = ((Users) o).getId();
		try {
			member.setNickname(nickname);
			member.setGender(gender);
			member.setAge(age);
			member.setHeight(height);
			member.setWeight(weight);
			member.setBodyFat(bodyFat);
			member.setVisceralFat(visceralFat);
			member.setMuscleMass(muscleMass);
			member.setBecomeVIP(becomeVIP);
			member.setPhoto(photo.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Member m1 = memberR.save(member);
		Integer id = m1.getMember_id();
		Member fkMember = memberR.findById(id).get();
		Users user = userR.findById(user_id).get();
		user.setFkMember(fkMember);
		userR.save(user);
		String str = "redirect:/member/inputshow?member_id=" + id;
		return str;
	}

	// @PostMapping("/member/inputpage")
	// @ResponseBody
	// public String memberInputPage(
	// // @RequestParam(name = "nickname", required = false) String nickname,
	// // @RequestParam(name = "gender", required = false) Integer gender,
	// // @RequestParam(name = "age", required = false) Integer age,
	// // @RequestParam(name = "height", required = false) Double height,
	// // @RequestParam(name = "weight", required = false) Double weight,
	// // @RequestParam(name = "bodyFat", required = false) Double bodyFat,
	// // @RequestParam(name = "visceralFat", required = false) Double visceralFat,
	// // @RequestParam(name = "muscleMass", required = false) Double muscleMass,
	// // @RequestParam(name = "becomeVIP", required = false) Integer becomeVIP
	// @RequestBody Member member) {
	// // Member member = new Member();
	// Object o =
	// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	// Integer user_id = ((Users) o).getId();
	// // try {
	// // member.setNickname(nickname);
	// // member.setGender(gender);
	// // member.setAge(age);
	// // member.setHeight(height);
	// // member.setWeight(weight);
	// // member.setBodyFat(bodyFat);
	// // member.setVisceralFat(visceralFat);
	// // member.setMuscleMass(muscleMass);
	// // member.setBecomeVIP(becomeVIP);
	// // } catch (Exception e) {
	// // e.printStackTrace();
	// // }
	// // Member m1 = memberR.save(member);
	// // Integer id = m1.getMember_id();
	// // Member fkMember = memberR.findById(id).get();
	// Users user = userR.findById(user_id).get();
	// // ((Users) o).setFkMember(member);
	// user.setFkMember(member);
	// String url = "redirect:/member/inputshow/" +
	// user.getFkMember().getMember_id();
	// userR.save(user);

	// return url;
	// // return str;
	// // return "/member/inputshow";
	// }

	// 註冊完，輸入完會員資料，抓取會員資料
	@GetMapping("/member/inputshow")
	public String memberinputshow(@RequestParam(name = "member_id") Integer id, Model m) {
		Optional<Member> m1 = memberR.findById(id);
		m.addAttribute("member", m1.orElse(null));
		return "/member/memberInputShow";
	}

	// 登入(userId & memberID)抓取會員資料
	@GetMapping("/member/page")
	public String memberPage(Model m) {
		// 取Users物件
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			// 把Users強制轉型成Bean，並抓member_ID
			Integer member_id = ((Users) o).getFkMember().getMember_id();
			System.out.println("find!");
			// 透過member_ID去找相關的User
			Optional<Member> m1 = memberR.findById(member_id);
			// 存入並抓取member資料
			m.addAttribute("member", m1.get());
			// 去抓會員資料
			return "/member/memberPage";
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 去輸入會員資料頁面
		return "/member/memberInputPage";
	}

	// @GetMapping("/member/page")
	// public String memberPage2(Model m) {
	// Object o =
	// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	// Integer member_id = ((Users) o).getFkMember().getMember_id();
	// Optional<Member> member = memberR.findById(member_id);
	// if (member != null) {
	// m.addAttribute("member", member.get());
	// return "/member/memberPage";
	// } else {
	// return "/member/inputpage";
	// }
	// }
	// ----------memberPage2 & memberPage3結果一樣，方法不同----------
	// @GetMapping("/member/page")
	// public String memberPage3(Model m) {
	// Object o =
	// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	// Integer member_id = ((Users) o).getFkMember().getMember_id();
	// List<Member> m1 = memberR.findListById(member_id);
	// if (m1 != null && m1.size() > 0) {
	// Optional<Member> member = memberR.findById(member_id);
	// m.addAttribute("member", member.get());
	// return "/member/memberPage";
	// } else {
	// return "/member/inputpage";
	// }
	// }

	// 編輯會員資料
	@GetMapping("/member/edit")
	public String memberEdit(Model m) {
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer member_id = ((Users) o).getFkMember().getMember_id();
		Optional<Member> m1 = memberR.findById(member_id);
		m.addAttribute("member", m1.get());
		return "/member/memberEdit";
	}

	@PostMapping("/member/edit")
	public String memberEditPost(@RequestParam(name = "member_id") Integer member_id,
			@RequestParam(name = "nickname", required = false) String nickname,
			@RequestParam(name = "gender", required = false) Integer gender,
			@RequestParam(name = "age", required = false) Integer age,
			@RequestParam(name = "height", required = false) Double height,
			@RequestParam(name = "weight", required = false) Double weight,
			@RequestParam(name = "bodyFat", required = false) Double bodyFat,
			@RequestParam(name = "visceralFat", required = false) Double visceralFat,
			@RequestParam(name = "muscleMass", required = false) Double muscleMass,
			@RequestParam(name = "becomeVIP", required = false) Integer becomeVIP,
			@RequestParam(name = "photo", required = false) MultipartFile photo,
			Model m) {
		Member member = new Member();

		try {
			member.setNickname(nickname);
			member.setGender(gender);
			member.setAge(age);
			member.setHeight(height);
			member.setWeight(weight);
			member.setBodyFat(bodyFat);
			member.setVisceralFat(visceralFat);
			member.setMuscleMass(muscleMass);
			member.setBecomeVIP(becomeVIP);
			member.setPhoto(photo.getBytes());
			memberR.updateById(member_id, nickname, gender, age, height, weight, bodyFat,
					visceralFat, muscleMass, becomeVIP, photo.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/member/page";
	}

}
