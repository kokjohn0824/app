package com.finalpretty.app.order.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finalpretty.app.Response.OrderDetailDto;
import com.finalpretty.app.Response.OrderDto;
import com.finalpretty.app.order.service.OrderDetailService;
import com.finalpretty.app.order.service.OrderService;
import com.finalpretty.app.request.MemberOrderDto;

@Controller
public class OrderController {

    @Autowired
    private OrderService oService;

    @Autowired
    private OrderDetailService detailService;

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
    public Boolean addOrder(@RequestBody List<MemberOrderDto> memDto) {
        // oService.addOrder(memDto);
        OrderDto orderDto = new OrderDto();
        List<OrderDetailDto> detailDto = null;
        for (MemberOrderDto mem : memDto) {
            if (mem.getOrderDto() != null) {
                orderDto = mem.getOrderDto();
            }
            detailDto = mem.getDetailDto();
        }
        Integer order_id = oService.addOrder(orderDto);
        // detailService.addDetail(null, order_id);
        return detailService.addDetail(detailDto, order_id);
        // return true;
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
