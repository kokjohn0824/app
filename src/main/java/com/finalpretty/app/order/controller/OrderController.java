package com.finalpretty.app.order.controller;

import java.io.Console;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finalpretty.app.Response.OrderDetailDto;
import com.finalpretty.app.Response.OrderDto;
import com.finalpretty.app.ecpay.payment.integration.AllInOne;
import com.finalpretty.app.ecpay.payment.integration.domain.AioCheckOutALL;
import com.finalpretty.app.model.Order;
import com.finalpretty.app.order.service.OrderDetailService;
import com.finalpretty.app.order.service.OrderService;
import com.finalpretty.app.repositories.OrderRespository;
import com.finalpretty.app.request.MemberOrderDto;

@Controller
public class OrderController {

    @Autowired
    private OrderService oService;

    @Autowired
    private OrderDetailService detailService;

    @Autowired
    private OrderRespository oDao;

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
    @PostMapping("/api/addOrder")
    public List<Integer> addOrder(@RequestBody List<MemberOrderDto> memDto) {
        // oService.addOrder(memDto);
        OrderDto orderDto = new OrderDto();
        List<OrderDetailDto> detailDto = new ArrayList<>();
        for (MemberOrderDto mem : memDto) {
            if (mem.getOrderDto() != null) {
                orderDto = mem.getOrderDto();
            }
            detailDto = mem.getDetailDto();
        }
        Integer total = 0;
        for (OrderDetailDto dto : detailDto) {
            total = total + dto.getTotal();
            // System.out.println("++++++++++++++++++");
            // System.out.println(dto.getProduct_name());
        }
        Integer order_id = oService.addOrder(orderDto, total);
        // detailService.addDetail(null, order_id);
        detailService.addDetail(detailDto, order_id);

        // if (orderDto.getPaid() == 2) {
        // return "redirect:/ecpay/test/";
        // }
        List<Integer> num = new ArrayList<>();
        num.add(orderDto.getPaid());
        num.add(order_id);
        for (Integer n : num) {
            System.out.println(n);
        }
        return num;
    }

    @GetMapping("/memberOrder")
    public String memberOrder() {
        return "/member/memberOrder";
    }

    // 成立訂單
    @ResponseBody
    @GetMapping("/public/findMemberOrder/{member_id}")
    public List<OrderDto> findMemberOrder(@PathVariable(name = "member_id") Integer member_id) {
        return oService.findMemberOrder(member_id);
    }

    // 綠界付款
    public static AllInOne all;

    @GetMapping("/successPaid/{order_id}")
    public String successPaid(@PathVariable("order_id") Integer order_id) {
        oService.updatePayment(order_id);
        return "/order/success";
    }

    @GetMapping("/ecpay/test/{order_id}")
    @ResponseBody
    public String testEcpay(@PathVariable("order_id") Integer order_id) {

        Optional<Order> order = oDao.findById(order_id);
        try {
            all = new AllInOne(" ");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            AioCheckOutALL obj = new AioCheckOutALL();
            // FIXME:測試訂單應該要隨機生成字串
            obj.setMerchantTradeNo("testNo" + Long.toString(order.get().getOrder_num()));
            obj.setMerchantTradeDate(dateFormat.format(date));
            obj.setTotalAmount(Integer.toString(order.get().getTotal()));
            obj.setTradeDesc("test Description");
            obj.setItemName("test");
            // TODO:新增付款成功頁面
            obj.setReturnURL("http://localhost:8082/successPaid/" + order_id);
            obj.setNeedExtraPaidInfo("N");
            obj.setClientBackURL("http://localhost:8082/");
            String form = all.aioCheckOut(obj, null);
            return form;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
