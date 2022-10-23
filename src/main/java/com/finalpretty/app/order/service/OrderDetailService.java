package com.finalpretty.app.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalpretty.app.model.Order_detail;
import com.finalpretty.app.repositories.Order_detailRespository;

@Service
public class OrderDetailService {

    @Autowired
    private Order_detailRespository odtDao;

    public List<Order_detail> findByFKOrderId(Integer order_id) {
        return odtDao.findByFKOrderId(order_id);
    }

}
