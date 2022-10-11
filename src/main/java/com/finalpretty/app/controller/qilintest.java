package com.finalpretty.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class qilintest {
	
	@GetMapping("/qilin")
    public String hello1() {
        return "qilintest";
    }

}
