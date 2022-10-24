package com.finalpretty.app.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.finalpretty.app.model.Product;
import com.finalpretty.app.product.service.ProductService;
import com.finalpretty.app.repositories.ProductRespository;

@Controller
// @RequestMapping("/storefrontend")
public class StoreFrontendController {

    @Autowired
    private ProductService pService;

    @GetMapping("/storefrontendone")
    public String addProduct() {
        return "product/storefrontend";
    }

    // 前台
    // 顯示全部文章

    @GetMapping("/storefrontend")
    public String getAllProduct(Model model) {
        List<Product> list = pService.findAll();

        for (Product li : list) {
            System.out.println(li.getPicture());
        }
        model.addAttribute("productlist", list);

        return "/product/storefrontend";
    }

}