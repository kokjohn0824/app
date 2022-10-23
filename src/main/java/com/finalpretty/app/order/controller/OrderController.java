package com.finalpretty.app.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finalpretty.app.model.Order;
import com.finalpretty.app.order.service.OrderService;

@Controller
public class OrderController {

    @Autowired
    private OrderService oService;

    // @GetMapping("/admin/orderAll")
    @GetMapping("/public/orderAll")
    public @ResponseBody List<Order> orderAll() {
        return oService.orderAll();
    }

    @GetMapping("/public/allOrder")
    public String allOrder() {
        return "/order/orderAll";
    }

}
