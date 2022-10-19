package com.finalpretty.app.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class hello {

    // 範例網站的視圖解析Controller
    @GetMapping("/hello")
    public String hello1(Model m) {
        // 取得username
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (o instanceof UserDetails) {
            username = ((UserDetails) o).getUsername();
        } else {
            username = o.toString();
        }
        m.addAttribute("loginusername", username);
        return "/hellofolder/hello";
    }

    // 前往後臺
    @GetMapping("/backEnd")
    public String backEnd() {
        return "backEnd";
    }

    @GetMapping("/Manager")
    public String Manager1() {
        return "helloManager";
    }

    @PostMapping("/hello")
    public String hellosubmit(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            Model m) {
        String username = firstName + lastName;
        m.addAttribute("username", username);
        return "hello";
    }

    // CKeditor編輯器的結果回傳視圖編輯器
    @PostMapping("/editorResult")
    public String ckeditorResult(@RequestParam String content, Model m) {
        m.addAttribute("content", content);
        return "editorResult";
    }

    @GetMapping("/hihi")
    @ResponseBody
    public String hello2() {
        return "hihi";
    }

}
