package com.finalpretty.app.product.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.finalpretty.app.repositories.ProductRespository;

@Controller
public class DefaultPhotoController {

    @Autowired
    private ProductRespository pDao;

    @GetMapping("/gotodefaultPhoto")
    public String gotoDefault() {
        return "/defaultPhoto";
    }

    @PostMapping("/defaultPhoto")
    public String defaultPhoto(@RequestParam(name = "file") MultipartFile file) {
        try {
            pDao.defaultPhoto(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
