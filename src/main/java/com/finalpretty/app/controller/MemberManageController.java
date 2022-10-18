package com.finalpretty.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalpretty.app.model.Member;
import com.finalpretty.app.repositories.MemberRespository;

@Controller
public class MemberManageController {

	@Autowired
	private MemberRespository memberR;
	
//	@GetMapping("/member/manage")
//	public String memberManagePage() {
//		return "memberManage";
//	}
	
	
	//搜尋全部
	// FIXME: /memberManage 爆掉了
	@GetMapping("/member/manage")
	public String memberGetAll(Model m){
		List<Member> list = memberR.findAll();
		m.addAttribute("list", list);
		return "memberManage";
	}
	
	
	//刪除
	@GetMapping("/member/delete")
	public String memberDelete(@RequestParam(name = "id") Integer id) {
		memberR.deleteById(id);	
		return "redirect:/member/manage";
	}
	
	
	//編輯
	
	
	
}
