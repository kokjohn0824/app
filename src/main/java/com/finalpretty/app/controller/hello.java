package com.finalpretty.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class hello {
    
    @GetMapping("/hello")
    public String hello1() {
        return "hello";
    }

    @GetMapping("/hihi")
    @ResponseBody
    public String hello2() {
        return "hihi";
    }
}
