package com.finalpretty.app.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finalpretty.app.model.Order_detail;
import com.finalpretty.app.order.service.OrderDetailService;

@Controller
public class OrderDetailController {

    @Autowired
    private OrderDetailService odtService;

    @GetMapping("/public/findByFKOrderId/{order_id}")
    public @ResponseBody List<Order_detail> findByFKOrderId(@PathVariable("order_id") Integer order_id) {
        return odtService.findByFKOrderId(order_id);
    }

    @GetMapping("/public/detailOrder")
    public String detailOrder() {
        return "/detailOrder";
    }

}