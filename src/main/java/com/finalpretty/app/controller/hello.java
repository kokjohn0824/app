package com.finalpretty.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class hello {
    
    @GetMapping("/hello")
    public String hello1() {
        return "hello";
    }
    
    @PostMapping("/hello")
    public String hellosubmit(
        @RequestParam(required = false) String firstName,
        @RequestParam(required = false) String lastName,
        Model m) {
        String username = firstName+lastName;
        m.addAttribute("username",username);    
        return "hello";
    }


    @GetMapping("/hihi")
    @ResponseBody
    public String hello2() {
        return "hihi";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
