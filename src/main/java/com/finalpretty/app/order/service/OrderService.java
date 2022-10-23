package com.finalpretty.app.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalpretty.app.model.Order;
import com.finalpretty.app.repositories.OrderRespository;

@Service
public class OrderService {

    @Autowired
    private OrderRespository oDao;

    public List<Order> orderAll() {
        return oDao.findAll();
    }

}
