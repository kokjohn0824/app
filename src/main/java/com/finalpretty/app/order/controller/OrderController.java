package com.finalpretty.app.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finalpretty.app.model.Order;
import com.finalpretty.app.order.service.OrderDto;
import com.finalpretty.app.order.service.OrderService;
import com.finalpretty.app.security.UsersRepository;

@Controller
public class OrderController {

    @Autowired
    private OrderService oService;

    @Autowired
    private UsersRepository uDao;

    // @GetMapping("/admin/orderAll")
    @GetMapping("/public/orderAll")
    public @ResponseBody List<OrderDto> orderAll() {
        return oService.orderAll();
    }

    // 後台訂單查看
    @GetMapping("/public/allOrder")
    public String allOrder() {
        return "/order/orderAll";
    }

    @ResponseBody
    @PostMapping("/public/api/addOrder")
    public String addOrder(@RequestBody List<OrderDto> orderDto) {
        for (OrderDto i : orderDto) {
            System.out.println(i.getOrder_id());
            System.out.println(i.getOrder_num());
            System.out.println(i.getPaid());
            System.out.println(i.getShip());
            System.out.println(i.getTotal());
            System.out.println(i.getCreate_date());
            System.out.println(i.getAddress());
            System.out.println(i.getFk_member_id());
        }
        return "hi";
    }

    @GetMapping("/memberOrder")
    public String memberOrder() {
        return "/member/memberOrder";
    }

    @ResponseBody
    @GetMapping("/public/findMemberOrder/{member_id}")
    public List<OrderDto> findMemberOrder(@PathVariable(name = "member_id") Integer member_id) {
        return oService.findMemberOrder(member_id);
    }

}
