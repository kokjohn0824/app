package com.finalpretty.app.product.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finalpretty.app.model.Product;
import com.finalpretty.app.product.service.ProductService;

@Controller
// @RequestMapping("/storefrontend")
public class StoreFrontendController {

    @Autowired
    private ProductService pService;

    @GetMapping("/public/storefrontendone")
    public String addProduct() {
        return "product/storefrontend";
    }

    // 前台
    // 顯示全部

    @GetMapping("/cartCheck")
    public String cartCheck() {
        return "/product/cartcheck";
    }

    // 找食品
    @GetMapping("/public/eatproduct")
    public String selectByEatProduct(Model model) {
        List<Product> list = pService.selectByEatProduct();

        for (Product li : list) {
            System.out.println(li.getPicture());
        }
        model.addAttribute("productlisteat", list);

        return "/product/eatproduct";
    }

    // 找用品
    @GetMapping("/public/useproduct")
    public String selectByUseProduct(Model model) {
        List<Product> list = pService.selectByUseProduct();

        for (Product li : list) {
            // System.out.println(li.getPicture());
        }
        model.addAttribute("productlistuse", list);

        return "/product/useproduct";
    }

    // 秀全部商品
    @GetMapping("/public/storefrontend")
    public String getAllProduct(Model model) {
        List<Product> list = pService.findAllByOnSale();

        for (Product li : list) {
            System.out.println(li.getPicture());
        }
        model.addAttribute("productlist", list);

        return "/product/storefrontend";
    }

    // 模糊搜尋
    @ResponseBody
    @PostMapping("/public/product/fuzzySearch")
    public List<Product> fuzzySearchArticle(@RequestBody String likeTest) {
        List<Product> list = pService.fuzzySearch(likeTest);
        return list;
    }

    @GetMapping("/public/downloadImage/{id}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Integer id) {
        // System.out.println(id);
        Product product = pService.findById(id);

        byte[] photoFile = product.getPicture();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(photoFile, headers, HttpStatus.OK);
    }
}