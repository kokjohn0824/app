package com.finalpretty.app.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
			@RequestParam(name = "nickname") String nickname,
			@RequestParam(name = "gender") Integer gender,
			@RequestParam(name = "age") Integer age,
			@RequestParam(name = "height") double height,
			@RequestParam(name = "weight") double weight,
			@RequestParam(name = "bodyFat") double bodyFat,
			@RequestParam(name = "visceralFat") double visceralFat,
			@RequestParam(name = "muscleMass") double muscleMass,
			@RequestParam(name = "becomeVIP") Integer becomeVIP) {
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

	// 使用分頁
	// public Page<Member> findByPage(Integer pageNumber) {
	// Pageable pgb = PageRequest.of(pageNumber - 1, 3, Sort.Direction.DESC,
	// "added");
	// Page<Member> page = memberR.findAll(pgb);
	// return page;
	// }

	// public Member findLasttest() {
	// return memberR.findFirstByOrderByAddedDesc();
	// }

	// @RequestMapping("/backendMember/showAll")
	// public String showMember(@RequestParam(name = "p", defaultValue = "1")
	// Integer pageNumber, Model model) {
	// Page<Member> page = findByPage(pageNumber);
	// model.addAttribute("page", page);
	// return "/member/backendMemberShowAll";
	// }
	// 分頁怪怪的
	// @RequestMapping("/backendMember/showAll")
	// public String memeberShowAll(@RequestParam(value = "page", defaultValue =
	// "0") Integer page,
	// @RequestParam(value = "size", defaultValue = "3") Integer size, Model model)
	// {
	// Sort sort = Sort.by(Sort.Direction.DESC, "member_id");
	// Pageable pageable = PageRequest.of(page, size, sort);
	// Page<Member> members = memberR.findList(pageable);
	// model.addAttribute("members", members);
	// return "/member/backendMemberShowAll";
	// }

	// 分頁
	// @GetMapping("/backendMember/showAll")
	// public String list(@RequestParam(value = "pageNum", defaultValue = "0")
	// Integer pageNum,
	// @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize, Model
	// model) {
	// // Page<Member> members = memberR.getMemberList(pageNum, pageSize);
	// Sort sort = Sort.by(Sort.Direction.DESC, "member_id");
	// Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
	// Page<Member> members = memberR.findList(pageable);
	// // Page<Member> members = memberR.findAll(pageable);
	// model.addAttribute("members", members);
	// return "/member/backendMemberShowAll";
	// }

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
			@RequestParam(name = "nickname") String nickname,
			@RequestParam(name = "gender") Integer gender,
			@RequestParam(name = "age") Integer age,
			@RequestParam(name = "height") double height,
			@RequestParam(name = "weight") double weight,
			@RequestParam(name = "bodyFat") double bodyFat,
			@RequestParam(name = "visceralFat") double visceralFat,
			@RequestParam(name = "muscleMass") double muscleMass,
			@RequestParam(name = "becomeVIP") Integer becomeVIP,
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
			@RequestParam(name = "nickname") String nickname,
			@RequestParam(name = "gender") Integer gender,
			@RequestParam(name = "age") Integer age,
			@RequestParam(name = "height") double height,
			@RequestParam(name = "weight") double weight,
			@RequestParam(name = "bodyFat") double bodyFat,
			@RequestParam(name = "visceralFat") double visceralFat,
			@RequestParam(name = "muscleMass") double muscleMass,
			@RequestParam(name = "becomeVIP") Integer becomeVIP) {
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
		// return "/member/inputpage";
	}

	// 註冊完，輸入完會員資料，抓取會員資料
	@GetMapping("/member/inputshow")
	public String memberPage(@RequestParam(name = "member_id") Integer id, Model m) {
		Optional<Member> m1 = memberR.findById(id);
		m.addAttribute("member", m1.orElse(null));
		return "/member/memberInputShow";
	}

	// 登入(userId & memberID)抓取會員資料、並可以編輯
	@GetMapping("/member/page")
	public String memberPage(Model m) {
		// 取Users物件
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			// 把Users強制轉型成Bean，並抓member_ID
			Integer member_id = ((Users) o).getFkMember().getMember_id();
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
		return "/member/inputpage";
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
	// memberPage2 & memberPage3結果一樣，方法不同
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

	// List<Member> member = memberR.findListById(member_id);
	// // 判斷member 是否有值，有就導入，沒有就去輸入頁面
	// if (member != null && member.size() > 0) {
	// //
	// Optional<Member> member = memberR.findById(member_id);
	// return "/member/memberPage";
	// } else {
	// return "/member/inputpage";
	// }

}
