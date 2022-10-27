package com.finalpretty.app.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.finalpretty.app.model.DailyRecord;

public interface DailyRecordRespository extends JpaRepository<DailyRecord, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update daily_record set [weight]= :weight, bodyFat= :bodyFat, drinkingWater= :drinkingWater where daily_record_id= :daily_record_id", nativeQuery = true)
    void updateById(
            @Param("daily_record_id") Integer daily_record_id,
            @Param("weight") Integer weight,
            @Param("bodyFat") Integer bodyFat,
            @Param("drinkingWater") Integer drinkingWater);
    // @Param("Date_time") String Date_time
}
