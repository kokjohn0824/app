package com.finalpretty.app.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/storefrontend")
public class StoreFrontendController {

    @GetMapping
    public String addProduct() {
        return "product/storefrontend";
    }

    
    @GetMapping("/findAllProduct")
    public String findAllproduct() {
        return "product/productAll";
    }
    
}