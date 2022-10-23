package com.finalpretty.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.finalpretty.app.model.Order;

public interface OrderRespository extends JpaRepository<Order, Integer> {

    @Query(value = "select * from [order] order by create_date desc", nativeQuery = true)
    List<Order> findAll();

}
