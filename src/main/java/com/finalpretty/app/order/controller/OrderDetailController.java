package com.finalpretty.app.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finalpretty.app.Response.OrderDetailDto;
import com.finalpretty.app.order.service.OrderDetailService;

@Controller
public class OrderDetailController {

    @Autowired
    private OrderDetailService odtService;

    @GetMapping("/public/findByFKOrderId/{order_id}")
    public @ResponseBody List<OrderDetailDto> findByFKOrderId(@PathVariable("order_id") Integer order_id) {
        return odtService.findByFKOrderId(order_id);
    }

    @GetMapping("/public/detailOrder")
    public String detailOrder() {
        return "/order/detailOrder";
    }

    // @GetMapping("/public/detailOrder/{order_id}")
    // public String detailOrder(@PathVariable("order_id") Integer order_id, Model
    // m) {
    // List<Order_detail> list = odtService.findByFKOrderId(order_id);
    // m.addAttribute("list", list);
    // return "/order/detailOrder";
    // }

}