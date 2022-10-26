package com.finalpretty.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.finalpretty.app.Response.OrderDto;
import com.finalpretty.app.model.Order;

public interface OrderRespository extends JpaRepository<Order, Integer> {

    @Query(value = "select * from [order] order by create_date desc", nativeQuery = true)
    List<Order> findAll();

    @Query(value = "select * from [order] where fk_member_id = :member_id", nativeQuery = true)
    List<Order> findMemberOrder(@Param("member_id") Integer member_id);

    @Query(value = "select TOP 1 * from [order] where fk_member_id = :member_id order by order_id DESC", nativeQuery = true)
    Order findOrderByNew(@Param("member_id") Integer member_id);
}
