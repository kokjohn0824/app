package com.finalpretty.app.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.finalpretty.app.model.DailyRecord;

public interface DailyRecordRespository extends JpaRepository<DailyRecord, Integer> {

    @Transactional
    @Modifying
    @Query(value = "select * from daily_record where fk_member_id=:member_id and Date_time=:today", nativeQuery = true)
    List<DailyRecord> selectRecord(
            @Param("member_id") Integer member_id,
            @Param("today") String today);

    @Query(value = "select * from daily_record where Date_time=:date_time", nativeQuery = true)
    Optional<DailyRecord> findByDate(@Param("date_time") String date_time);

    @Query(value = "select * from daily_record where fk_member_id = :member_id and date_time between :addDate and :dateEnd", nativeQuery = true)
    List<DailyRecord> chartBmi(@Param("member_id") Integer member_id, @Param("addDate") String addDate,
            @Param("dateEnd") String dateEnd);
}
