package com.finalpretty.app.repositories;

import java.util.List;

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

    @Query(value = "select sportsname form sports", nativeQuery = true)
    List<Sports> findSportsName();

    @Query(value = "select sports_id form sports where sportsname=:sportsname", nativeQuery = true)
    Integer findByName(@Param("sportsname") String sportsname);
}
