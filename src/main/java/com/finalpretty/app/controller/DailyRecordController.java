package com.finalpretty.app.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finalpretty.app.Response.DailyFoodResponse;
import com.finalpretty.app.model.DailyRecord;
import com.finalpretty.app.model.Food;
import com.finalpretty.app.model.Food_daily;
import com.finalpretty.app.model.Member;
import com.finalpretty.app.model.Users;
import com.finalpretty.app.repositories.DailyRecordRespository;
import com.finalpretty.app.repositories.FoodDailyRespository;
import com.finalpretty.app.repositories.FoodRespository;
import com.finalpretty.app.repositories.MemberRespository;

@Controller
public class DailyRecordController {

    @Autowired
    private MemberRespository memberR;

    @Autowired
    private DailyRecordRespository dailyRecordR;

    @Autowired
    private FoodRespository foodR;

    @Autowired
    private FoodDailyRespository foodDailyR;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    // 前台
    // 顯示用戶全部日記
    @GetMapping("/dailyRecord/all")
    public String manageDailyRecordPage(Model m) {
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer member_id = ((Users) o).getFkMember().getMember_id();

        Date date = new Date();
        String today = dateFormat.format(date);

        Member member = memberR.findById(member_id).get();
        Set<DailyRecord> daily_record = member.getDaily_records();
        List<DailyRecord> list = new ArrayList<DailyRecord>();
        list.addAll(daily_record);

        List<DailyRecord> dailyRecord = dailyRecordR.selectRecord(member_id, today);
        System.out.println(dailyRecord);

        if (dailyRecord != null && dailyRecord.size() > 0) {
            m.addAttribute("bool", "false");
        } else {
            m.addAttribute("bool", "true");
        }

        m.addAttribute("list", list);
        return "/dailyRecord/frontEndManageDaily";

    }

    // 前往新增日記
    @GetMapping("/dailyRecord/add")
    public String goAddDaily(Model model) {
        DailyRecord daily = new DailyRecord();
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer member_id = ((Users) o).getFkMember().getMember_id();
        Member member = memberR.findById(member_id).get();

        try {
            daily.setMembers(member);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dailyRecordR.save(daily);
        Integer Daily_record_id = daily.getDaily_record_id();
        List<Food> foodList = foodR.findAll();
        Optional<DailyRecord> a1 = dailyRecordR.findById(Daily_record_id);
        model.addAttribute("foodList", foodList);
        model.addAttribute("daily_record", a1.get());
        return "/dailyRecord/frontEndAddDailyPage";
    }

    // 新增日記
    @PostMapping("/dailyRecord/add")
    public String addDaily(
            @RequestParam(name = "daily_record_id") Integer daily_record_id,
            @RequestParam(name = "weight") Integer weight,
            @RequestParam(name = "bodyFat") Integer bodyFat,
            @RequestParam(name = "drinkingWater") Integer drinkingWater) {
        Date date = new Date();
        String date_time = dateFormat.format(date);

        DailyRecord dailyRecord = dailyRecordR.findById(daily_record_id).get();

        dailyRecord.setWeight(weight);
        dailyRecord.setBodyFat(bodyFat);
        dailyRecord.setDrinkingWater(drinkingWater);
        dailyRecord.setDate_time(date_time);

        dailyRecordR.save(dailyRecord);
        return "redirect:/dailyRecord/all";
    }

    // 刪除日記(by daily_record_id)
    @GetMapping("/dailyRecord/delete")
    public String deleteDaily(@RequestParam(name = "daily_record_id") Integer daily_record_id) {
        DailyRecord dailyRecord = dailyRecordR.findById(daily_record_id).get();
        dailyRecord.setMembers(null);
        dailyRecordR.save(dailyRecord);
        dailyRecordR.deleteById(daily_record_id);
        return "redirect:/dailyRecord/all";
    }

    // 前往修改今天日記
    @GetMapping("/todayDailyRecord/edit")
    public String goTodayEditDaily(Model model) {
        Date date = new Date();
        String date_time = dateFormat.format(date);
        Optional<DailyRecord> a1 = dailyRecordR.findByDate(date_time);
        model.addAttribute("daily_record", a1.orElse(null));
        return "/dailyRecord/frontEndEditDaily";
    }

    // 前往修改日記
    @GetMapping("/dailyRecord/edit")
    public String goEditDaily(@RequestParam(name = "date_time") String date_time,
            Model model) {
        Optional<DailyRecord> a1 = dailyRecordR.findByDate(date_time);
        model.addAttribute("daily_record", a1.orElse(null));
        return "/dailyRecord/frontEndEditDaily";
    }

    // 修改日記
    @PostMapping("/dailyRecord/edit")
    public String editDaily(
            @RequestParam(name = "daily_record_id") Integer daily_record_id,
            @RequestParam(name = "weight") Integer weight,
            @RequestParam(name = "bodyFat") Integer bodyFat,
            @RequestParam(name = "drinkingWater") Integer drinkingWater) {

        DailyRecord dailyRecord = dailyRecordR.findById(daily_record_id).get();

        dailyRecord.setWeight(weight);
        dailyRecord.setBodyFat(bodyFat);
        dailyRecord.setDrinkingWater(drinkingWater);

        dailyRecordR.save(dailyRecord);
        return "redirect:/dailyRecord/all";
    }

    // 新增食物
    @ResponseBody
    @PostMapping("/public/dailyRecord/addFood/{daily_record_id}/{food_id}/{side}")
    public DailyFoodResponse dailyAddFood(
            @PathVariable(name = "daily_record_id") Integer daily_record_id,
            @PathVariable(name = "food_id") Integer food_id,
            @PathVariable(name = "side") Integer side) {
        try {
            Food_daily food_daily = new Food_daily();

            DailyRecord DailyRecord = dailyRecordR.findById(daily_record_id).get();
            Food food = foodR.findById(food_id).get();

            Integer calorie = food.getCalorie();
            String foodname = food.getFoodname();
            Integer totle = calorie * side;

            food_daily.setDaily_record(DailyRecord);
            food_daily.setFood(food);
            food_daily.setSide(side);
            foodDailyR.save(food_daily);

            DailyFoodResponse ar = new DailyFoodResponse();
            ar.setFoodname(foodname);
            ar.setSide(side);
            ar.setTotle(totle);

            return ar;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
