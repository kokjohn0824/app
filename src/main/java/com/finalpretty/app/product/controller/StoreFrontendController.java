package com.finalpretty.app.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.finalpretty.app.model.Product;
import com.finalpretty.app.product.service.ProductService;

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
    // 顯示全部

    @GetMapping("/public/cartCheck")
    public String cartCheck() {
        return "/product/cartcheck";
    }

    // 找食品
    @GetMapping("/storefrontend/eatproduct")
    public String selectByEatProduct(Model model) {
        List<Product> list = pService.selectByEatProduct();

        for (Product li : list) {
            System.out.println(li.getPicture());
        }
        model.addAttribute("productlisteat", list);

        return "/product/storefrontend/eatproduct";
    }

    // 找用品
    @GetMapping("/storefrontend/useproduct")
    public String selectByUseProduct(Model model) {
        List<Product> list = pService.selectByUseProduct();

        for (Product li : list) {
            System.out.println(li.getPicture());
        }
        model.addAttribute("productlistuse", list);

        return "/product/storefrontend/useproduct";
    }

    // 秀全部商品
    @GetMapping("/storefrontend")
    public String getAllProduct(Model model) {
        List<Product> list = pService.findAllByOnSale();

        for (Product li : list) {
            System.out.println(li.getPicture());
        }
        model.addAttribute("productlist", list);

        return "/product/storefrontend";
    }

    @GetMapping("/downloadImage/{id}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Integer id) {
        System.out.println(id);
        Product product = pService.findById(id);

        byte[] photoFile = product.getPicture();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(photoFile, headers, HttpStatus.OK);
    }
}