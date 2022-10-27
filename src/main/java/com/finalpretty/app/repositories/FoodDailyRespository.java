package com.finalpretty.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalpretty.app.model.Food_daily;

public interface FoodDailyRespository extends JpaRepository<Food_daily, Integer> {

}
