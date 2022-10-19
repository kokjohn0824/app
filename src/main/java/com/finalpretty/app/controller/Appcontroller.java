package com.finalpretty.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// FIXME: 將appcontroller改大寫
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

    @GetMapping("/backEnd")
    public String backEnd() {
        return "backEnd";
    }

}
