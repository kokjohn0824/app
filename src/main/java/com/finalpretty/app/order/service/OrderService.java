package com.finalpretty.app.order.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalpretty.app.Response.OrderDto;
import com.finalpretty.app.model.Member;
import com.finalpretty.app.model.Order;
import com.finalpretty.app.repositories.MemberRespository;
import com.finalpretty.app.repositories.OrderRespository;

@Service
public class OrderService {

    @Autowired
    private OrderRespository oDao;
    @Autowired
    private MemberRespository memDao;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    public List<OrderDto> orderAll() {
        List<Order> oList = oDao.findAll();
        List<OrderDto> listDto = new ArrayList<>();
        OrderDto oDto = null;
        for (Order i : oList) {
            oDto = new OrderDto();
            oDto.setOrder_id(i.getOrder_id());
            oDto.setOrder_num(i.getOrder_num());
            oDto.setShip(i.getShip());
            oDto.setPaid(i.getPaid());
            oDto.setAddress(i.getAddress());
            oDto.setCreate_date(i.getCreate_date());
            oDto.setTotal(i.getTotal());
            listDto.add(oDto);
        }

        return listDto;
    }

    public List<OrderDto> findMemberOrder(Integer member_id) {
        List<Order> order = oDao.findMemberOrder(member_id);
        List<OrderDto> listOrder = new ArrayList<>();
        OrderDto oDto = null;
        if (order != null && !order.isEmpty()) {
            for (Order i : order) {
                oDto = new OrderDto();
                oDto.setOrder_id(i.getOrder_id());
                oDto.setOrder_num(i.getOrder_num());
                oDto.setPaid(i.getPaid());
                oDto.setShip(i.getShip());
                oDto.setTotal(i.getTotal());
                oDto.setAddress(i.getAddress());
                oDto.setCreate_date(i.getCreate_date());
                oDto.setFk_member_id(i.getMember().getMember_id());
                listOrder.add(oDto);
            }

            return listOrder;
        } else {
            return null;
        }
    }

    public Integer addOrder(OrderDto orderDto) {
        Date date = new Date();
        Order order = new Order();
        Optional<Member> mem = memDao.findById(orderDto.getFk_member_id());
        order.setMember(mem.get());
        order.setOrder_num(Long.parseLong(dateFormat.format(date)));
        order.setPaid(orderDto.getPaid());
        order.setShip(orderDto.getShip());
        order.setTotal(orderDto.getTotal());
        order.setCreate_date(date);
        order.setAddress(orderDto.getAddress());
        oDao.save(order);
        order = oDao.findOrderByNew(orderDto.getFk_member_id());
        return order.getOrder_id();
    }

}
