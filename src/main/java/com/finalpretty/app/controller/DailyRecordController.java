package com.finalpretty.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
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
import com.finalpretty.app.repositories.DailyRecordRespository;
import com.finalpretty.app.repositories.MemberRespository;

@Controller
public class DailyRecordController {

    @Autowired
    private MemberRespository memberR;

    @Autowired
    private DailyRecordRespository dailyRecordR;

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
    public String goAddDaily() {
        return "/dailyRecord/frontEndAddDailyPage";
    }

    @PostMapping("/dailyRecord/add")
    public String addDaily(
            @RequestParam(name = "weight") String weight,
            @RequestParam(name = "bodyFat") String bodyFat,
            @RequestParam(name = "drinkingWater") Integer drinkingWater,
            @RequestParam(name = "member_id") Integer member_id) {
        DailyRecord daily = new DailyRecord();
        Optional<Member> member = memberR.findById(member_id);

        try {
            daily.setWeight(member_id);
            daily.setBodyFat(member_id);
            daily.setDrinkingWater(drinkingWater);
            daily.setMembers(member.orElse(null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        dailyRecordR.save(daily);
        return "/daily/frontEndAddDailyPage";
    }

}
