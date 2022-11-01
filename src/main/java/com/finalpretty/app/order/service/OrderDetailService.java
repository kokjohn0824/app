package com.finalpretty.app.order.service;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalpretty.app.Response.OrderDetailDto;
import com.finalpretty.app.model.Order;
import com.finalpretty.app.model.Order_detail;
import com.finalpretty.app.model.Product;
import com.finalpretty.app.repositories.OrderRespository;
import com.finalpretty.app.repositories.Order_detailRespository;
import com.finalpretty.app.repositories.ProductRespository;

@Service
public class OrderDetailService {

    @Autowired
    private Order_detailRespository detailDao;

    @Autowired
    private OrderRespository oDao;

    @Autowired
    private ProductRespository pDao;

    public List<OrderDetailDto> findByFKOrderId(Integer order_id) {
        List<OrderDetailDto> odt = new ArrayList<OrderDetailDto>();
        OrderDetailDto dto = null;
        for (Order_detail i : detailDao.findByFKOrderId(order_id)) {
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

    public void addDetail(List<OrderDetailDto> detailDto, Integer order_id) {
        Order_detail detail;
        Optional<Order> order = oDao.findById(order_id);
        for (OrderDetailDto dDto : detailDto) {
            Optional<Product> product = pDao.findById(dDto.getFk_product_id());
            detail = new Order_detail();
            detail.setOrder(order.get());
            detail.setProduct(product.get());
            detail.setProduct_name(product.get().getTitle());
            detail.setCount(dDto.getCount());
            detail.setTotal(dDto.getTotal());
            pDao.updateVolume(product.get().getVolume() + dDto.getCount(), product.get().getProduct_id());
            detailDao.save(detail);
        }

    }

}
