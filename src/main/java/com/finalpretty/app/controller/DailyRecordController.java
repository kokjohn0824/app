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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalpretty.app.model.DailyRecord;
import com.finalpretty.app.model.Member;
import com.finalpretty.app.model.Users;
import com.finalpretty.app.order.service.OrderService;
import com.finalpretty.app.repositories.DailyRecordRespository;
import com.finalpretty.app.repositories.MemberRespository;

@Controller
public class DailyRecordController {

    @Autowired
    private MemberRespository memberR;

    @Autowired
    private DailyRecordRespository dailyRecordR;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    // 日記
    // 前台

    // 顯示用戶全部日記
    @GetMapping("/dailyRecord/all")
    public String manageDailyRecordPage(Model m) {
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer member_id;
        if (o instanceof Users) {
            member_id = ((Users) o).getFkMember().getMember_id();
        } else {
            member_id = 0;
        }
        Member member = memberR.findById(member_id).get();
        Set<DailyRecord> daily_record = member.getDaily_records();
        List<DailyRecord> list = new ArrayList<DailyRecord>();
        list.addAll(daily_record);
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
        Optional<DailyRecord> a1 = dailyRecordR.findById(Daily_record_id);
        System.out.println("a1.get().getDaily_record_id()");
        System.out.println(a1.get().getDaily_record_id());
        System.out.println("a1.get().getDaily_record_id()");
        model.addAttribute("daily_record", a1.get());
        return "/dailyRecord/frontEndAddDailyPage";
    }

    // // 修改日記
    // @GetMapping("/dailyRecord/edit")
    // public String editArticle(@RequestParam(name = "article_id") Integer id,
    // Model model) {
    // Optional<Article> a1 = articleR.findById(id);
    // model.addAttribute("article", a1.orElse(null));
    // return "/article/backEndEditArticle";

    @PostMapping("/dailyRecord/add")
    public String addDaily(
            @RequestParam(name = "daily_record_id") Integer daily_record_id,
            @RequestParam(name = "weight") Integer weight,
            @RequestParam(name = "bodyFat") Integer bodyFat,
            @RequestParam(name = "drinkingWater") Integer drinkingWater) {
        // DailyRecord daily = new DailyRecord();

        // long miliseconds = System.currentTimeMillis();
        Date date = new Date();
        String Date_time = dateFormat.format(date);
        // daily.setWeight(weight);
        // daily.setBodyFat(bodyFat);
        // daily.setDrinkingWater(drinkingWater);
        // System.out.println(Date_time);
        DailyRecord dailyRecord = dailyRecordR.findById(daily_record_id).get();
        dailyRecord.setWeight(weight);
        dailyRecord.setBodyFat(bodyFat);
        dailyRecord.setDrinkingWater(drinkingWater);
        dailyRecord.setDate_time(Date_time);

        // dailyRecordR.updateById(weight, bodyFat, drinkingWater, daily_record_id);

        dailyRecordR.save(dailyRecord);
        return "/dailyRecord/frontEndManageDaily";
    }

}
