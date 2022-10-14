package com.finalpretty.app.controller;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Appcontroller {
    
    @GetMapping
    public String index(){
        return "index";
    }
    
}
