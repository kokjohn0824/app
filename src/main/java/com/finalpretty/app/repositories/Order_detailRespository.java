package com.finalpretty.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.finalpretty.app.model.Order_detail;

public interface Order_detailRespository extends JpaRepository<Order_detail, Integer> {
    //
    @Query(value = "select odt.order_detail_id, odt.[count], odt.product_name,"
            + " odt.total, odt.fk_order_id, odt.fk_product_id"
            + " from [order] as o join order_detail as odt"
            + " on o.order_id = odt.fk_order_id where order_id = :order_id", nativeQuery = true)
    List<Order_detail> findByFKOrderId(@Param("order_id") Integer order_id);
}
