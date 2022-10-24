package com.finalpretty.app.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalpretty.app.model.Order_detail;
import com.finalpretty.app.repositories.Order_detailRespository;

@Service
public class OrderDetailService {

    @Autowired
    private Order_detailRespository odtDao;

    public List<OrderDetailDto> findByFKOrderId(Integer order_id) {
        List<OrderDetailDto> odt = new ArrayList<OrderDetailDto>();
        OrderDetailDto dto = null;
        for (Order_detail i : odtDao.findByFKOrderId(order_id)) {
            dto = new OrderDetailDto();
            dto.setOrder_detail_id(i.getOrder_detail_id());
            dto.setProduct_name(i.getProduct_name());
            dto.setCount(i.getCount());
            dto.setTotal(i.getTotal());
            dto.setFk_product_id(i.getProduct().getProduct_id());
            dto.setFk_order_id(i.getOrder().getOrder_id());
            odt.add(dto);
        }
        return odt;
    }

}
