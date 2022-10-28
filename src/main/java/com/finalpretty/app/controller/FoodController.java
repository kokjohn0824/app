package com.finalpretty.app.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.finalpretty.app.model.Food;
import com.finalpretty.app.repositories.FoodRespository;

@Controller
public class FoodController {

    @Autowired
    private FoodRespository foodR;

    // 後台
    // 顯示全部食物
    @GetMapping("/food/manage")
    public String manageFoodPage(Model m) {
        List<Food> list = foodR.findAll();
        m.addAttribute("list", list);
        return "/food/backEndManageFood";
    }

    // 顯示全部食物的圖片
    @GetMapping("/showFoodImage/{id}")
    public ResponseEntity<byte[]> showFoodImage(@PathVariable Integer id) {
        Food food = foodR.findById(id).get();
        byte[] photoFile = food.getPicture();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(photoFile, headers, HttpStatus.OK);
    }

    // 前往新增食物
    @GetMapping("/food/add")
    public String goAddFood() {
        return "/food/backEndAddFoodPage";
    }

    // 新增食物
    @PostMapping("/food/add")
    public String addFood(
            @RequestParam(name = "foodname") String foodname,
            @RequestParam(name = "calorie") Integer calorie,
            @RequestParam(name = "file") MultipartFile file) {
        Food food = new Food();
        try {
            food.setFoodname(foodname);
            ;
            food.setCalorie(calorie);
            ;
            food.setPicture(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        foodR.save(food);
        return "/food/backEndAddFoodPage";
    }

    // 修改食物
    @GetMapping("/food/edit")
    public String editFood(@RequestParam(name = "food_id") Integer food_id, Model model) {
        Optional<Food> f1 = foodR.findById(food_id);
        model.addAttribute("food", f1.orElse(null));
        return "/food/backEndEditFood";
    }

    @PostMapping("/food/edit")
    public String editArticlePost(
            @RequestParam(name = "food_id") Integer food_id,
            @RequestParam(name = "foodname") String foodname,
            @RequestParam(name = "calorie") Integer calorie,
            @RequestParam(name = "picture") MultipartFile picture,
            Model model) {
        Food food = new Food();
        try {
            byte[] picture2 = picture.getBytes();
            food.setPicture(picture2);
            food.setCalorie(calorie);
            food.setFoodname(foodname);
            foodR.updateById(food_id, foodname, calorie, picture2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/food/backEndManageFood";
    }

    // 刪除食物(by id)
    @GetMapping("/food/delete")
    public String deleteFood(@RequestParam(name = "food_id") Integer food_id) {
        foodR.deleteById(food_id);
        return "redirect:/food/manage";
    }

    // =============================================================================================
    // 前台
    // 生成食物下拉式選單
    @GetMapping("/food/all")
    public String deleteFood(Model m) {
        List<Food> foodList = foodR.findFoodName();
        System.out.println("++++++++++++++++++++++++++++");
        System.out.println(foodList);
        System.out.println("++++++++++++++++++++++++++++");
        m.addAttribute("foodList", foodList);
        return "/dailyRecord/frontEndAddDailyPage";
    }
}
