package com.finalpretty.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalpretty.app.model.DailyRecord;

public interface DailyRecordRespository extends JpaRepository<DailyRecord, Integer> {

}
