package com.finalpretty.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

	@GetMapping("/findAllProduct")
	public String findAllproduct() {
		
		
		return "productAll";
	}
	
}
