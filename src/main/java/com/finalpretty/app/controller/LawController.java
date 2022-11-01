package com.finalpretty.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public")
public class LawController {

    @GetMapping("/privacypolicy")
    public String privacyPolicy() {
        return "/law/privacypolicy";
    }
}
