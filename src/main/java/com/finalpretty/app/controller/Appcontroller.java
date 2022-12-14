package com.finalpretty.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Appcontroller {

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/public/register")
    public String register() {
        return "register";
    }

    @GetMapping("/backEnd")
    public String backEnd() {
        return "backEnd";
    }

}
