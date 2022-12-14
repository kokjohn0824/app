package com.finalpretty.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.finalpretty.app.model.Food;

public interface FoodRespository extends JpaRepository<Food, Integer> {
    @Transactional
    @Modifying
    @Query(value = "update food set foodname=:foodname, calorie=:calorie, picture=:picture where food_id=:food_id", nativeQuery = true)
    void updateById(@Param("food_id") Integer food_id,
            @Param("foodname") String foodname,
            @Param("calorie") Integer calorie,
            @Param("picture") byte[] picture);

    @Query(value = "select foodname from food", nativeQuery = true)
    List<Food> findFoodName();

    @Query(value = "select food_id from food where foodname=:foodname", nativeQuery = true)
    Integer findByName(@Param("foodname") String foodname);
}
