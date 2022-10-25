package com.finalpretty.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.finalpretty.app.model.Sports;

public interface SportsRespository extends JpaRepository<Sports, Integer> {
    @Transactional
    @Modifying
    @Query(value = "update sports set sportsname=:sportsname, calorie=:calorie, picture=:picture where sports_id=:sports_id", nativeQuery = true)
    void updateById(@Param("sports_id") Integer sports_id,
            @Param("sportsname") String sportsname,
            @Param("calorie") Integer calorie,
            @Param("picture") byte[] picture);
}
