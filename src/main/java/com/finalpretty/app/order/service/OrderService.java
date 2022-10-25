package com.finalpretty.app.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalpretty.app.model.Order;
import com.finalpretty.app.repositories.OrderRespository;

@Service
public class OrderService {

    @Autowired
    private OrderRespository oDao;

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

}
