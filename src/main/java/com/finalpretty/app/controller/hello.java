package com.finalpretty.app.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finalpretty.app.ecpay.payment.integration.AllInOne;
import com.finalpretty.app.ecpay.payment.integration.domain.AioCheckOutALL;
import com.finalpretty.app.model.Users;

@Controller
public class hello {

    @Autowired
    protected HttpServletRequest request;

    public static AllInOne all;

    // 範例網站的視圖解析Controller
    @GetMapping("/hello")
    @ResponseBody
    public String hello1(Model m, HttpServletRequest req) {

        // 取得username
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (o instanceof Users) {
            username = ((Users) o).getNickname();
        } else {
            username = "訪客";
        }
        m.addAttribute("loginusername", username);
        // return "/hellofolder/hello";
        return req.getServerName();
    }

    @GetMapping("/public/ecpay/test")
    @ResponseBody
    public String testEcpay(@RequestParam String mtn) {
        if (mtn == null) {
            mtn = "testComp124ssdsds1";
        }
        try {
            all = new AllInOne(" ");
            AioCheckOutALL obj = new AioCheckOutALL();
            // FIXME:測試訂單應該要隨機生成字串
            obj.setMerchantTradeNo(mtn);
            obj.setMerchantTradeDate("2017/01/01 08:05:23");
            obj.setTotalAmount("50");
            obj.setTradeDesc("test Description");
            obj.setItemName("TestItem");
            // TODO:新增付款成功頁面
            obj.setReturnURL("http://localhost:8082/");
            obj.setNeedExtraPaidInfo("N");
            obj.setClientBackURL("http://localhost:8082/");
            String form = all.aioCheckOut(obj, null);
            return form;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/Manager")
    public String Manager1() {
        return "helloManager";
    }

    @PostMapping("/hello")
    public String hellosubmit(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            Model m) {
        String username = firstName + lastName;
        m.addAttribute("username", username);
        return "hello";
    }

    // CKeditor編輯器的結果回傳視圖編輯器
    @PostMapping("/editorResult")
    public String ckeditorResult(@RequestParam String content, Model m) {
        m.addAttribute("content", content);
        return "editorResult";
    }

    @GetMapping("/admin/panel")
    public String panel() {
        return "adminpanel";
    }

}
