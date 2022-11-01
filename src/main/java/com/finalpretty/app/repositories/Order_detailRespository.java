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

        @Query(value = "insert into order_detail([count], product_name, total, fk_order_id , fk_product_id)"
                        + "values(:count, :product_name, :total, :fk_order_id, :fk_product_id)", nativeQuery = true)
        void insertDetail(@Param("count") Integer count, @Param("product_name") String product_name,
                        @Param("total") Integer total, @Param("fk_order_id") Integer fk_order_id,
                        @Param("fk_product_id") Integer fk_product_id);

        @Query(value = "select * from order_detail where fk_product_id = :product_id", nativeQuery = true)
        List<Order_detail> findByFkProductId(@Param("product_id") Integer product_id);

}
