package com.finalpretty.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalpretty.app.model.Member;
import com.finalpretty.app.repositories.MemberRespository;

@Controller
public class MemberManageController {

	@Autowired
	private MemberRespository memberR;
	
	@GetMapping("/member/page")
	public String memberManagePage() {
		return "/member/memberPage";
	}
	
	
	//新增會員
	@GetMapping("/member/add")
	public String addMember(Model m) {
		Member m1 = new Member();
		m.addAttribute("member", m1);
		List<Member> list = memberR.findAll();
		m.addAttribute("list", list);
		return "/member/addMember";
	}
	
	
	//搜尋全部
	@GetMapping("/member/manage")
	public String memberGetAll(Model m){
		List<Member> list = memberR.findAll();
		m.addAttribute("list", list);
		return "/member/memberManage";
	}
	
	
	//刪除
	@GetMapping("/member/delete")
	public String memberDelete(@RequestParam(name = "id") Integer id) {
		memberR.deleteById(id);	
		return "redirect:/member/manage";
	}
	
	
	//編輯
	
	
	
}
