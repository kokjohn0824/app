package com.finalpretty.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalpretty.app.model.Member;
import com.finalpretty.app.repositories.MemberRespository;

@Controller
public class MemberManageController {

	@Autowired
	private MemberRespository memberR;
	
//----------後台-----------------------------------------------------------------------	
	
	//後台主頁
	@GetMapping("/member/page")
	public String memberManagePage() {
		return "/member/memberPage";
	}
	
	
	//前往新增會員頁面
	@GetMapping("/member/add")
	public String addMemberPage() {
		return "/member/addMember";
	}
	
	//新增會員
	@PostMapping("/member/add")
	public String addMember(
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
		memberR.save(member);
		return "/member/addMember";
	}
	
	
	
	
	//顯示全部會員
	@GetMapping("/member/manage")
	public String memberGetAll(Model m){
		List<Member> list = memberR.findAll();
		m.addAttribute("list", list);
		return "/member/memberManage";
	}
	
	
	//刪除會員
	@GetMapping("/member/delete")
	public String memberDelete(@RequestParam(name = "id") Integer id) {
		memberR.deleteById(id);	
		return "redirect:/member/manage";
	}
	
	
//----------前台-----------------------------------------------------------------------	
	
	
	
}
