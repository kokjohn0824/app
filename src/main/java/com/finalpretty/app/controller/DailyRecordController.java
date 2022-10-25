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
import com.finalpretty.app.model.Sports;
import com.finalpretty.app.repositories.DailyRecordRespository;
import com.finalpretty.app.repositories.FoodRespository;
import com.finalpretty.app.repositories.SportsRespository;

@Controller
public class DailyRecordController {

    @Autowired
    private DailyRecordRespository dailyR;

    @Autowired
    private FoodRespository foodR;

    @Autowired
    private SportsRespository sportsR;

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // 食物
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
    public String editArticlePost(@RequestParam(name = "food_id") Integer food_id,
            @RequestParam(name = "foodname") String foodname,
            @RequestParam(name = "calorie") Integer calorie,
            @RequestParam(name = "file") MultipartFile picture,
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

    // ======================================================================================

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // 運動
    // 後台
    // 顯示全部運動
    @GetMapping("/sports/manage")
    public String manageSportsPage(Model m) {
        List<Sports> list = sportsR.findAll();
        m.addAttribute("list", list);
        return "/sports/backEndManageSports";
    }

    // 顯示全部運動的圖片
    @GetMapping("/showSportsImage/{id}")
    public ResponseEntity<byte[]> showSportsImage(@PathVariable Integer id) {
        Sports sports = sportsR.findById(id).get();
        byte[] photoFile = sports.getPicture();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(photoFile, headers, HttpStatus.OK);
    }

    // 前往新增運動
    @GetMapping("/sports/add")
    public String goAddSports() {
        return "/sports/backEndAddSportsPage";
    }

    // 新增運動
    @PostMapping("/sports/add")
    public String addSports(
            @RequestParam(name = "sportsname") String sportsname,
            @RequestParam(name = "calorie") Integer calorie,
            @RequestParam(name = "file") MultipartFile file) {
        Sports sports = new Sports();
        try {
            sports.setSportsname(sportsname);
            ;
            sports.setCalorie(calorie);
            ;
            sports.setPicture(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        sportsR.save(sports);
        return "/sports/backEndAddSportsPage";
    }

    // 修改運動
    @GetMapping("/sports/edit")
    public String editSports(@RequestParam(name = "sports_id") Integer sports_id, Model model) {
        Optional<Sports> s1 = sportsR.findById(sports_id);
        model.addAttribute("sports", s1.orElse(null));
        return "/sports/backEndEditSports";
    }

    @PostMapping("/sports/edit")
    public String editSportsPost(@RequestParam(name = "sports_id") Integer sports_id,
            @RequestParam(name = "sportsname") String sportsname,
            @RequestParam(name = "calorie") Integer calorie,
            @RequestParam(name = "file") MultipartFile picture,
            Model model) {
        Sports sports = new Sports();
        try {
            byte[] picture2 = picture.getBytes();
            sports.setPicture(picture2);
            sports.setCalorie(calorie);
            sports.setSportsname(sportsname);
            sportsR.updateById(sports_id, sportsname, calorie, picture2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/sports/backEndManageSports";
    }

    // 刪除運動(by id)
    @GetMapping("/sports/delete")
    public String deleteSports(@RequestParam(name = "sports_id") Integer sports_id) {
        foodR.deleteById(sports_id);
        return "redirect:/sports/manage";
    }

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
