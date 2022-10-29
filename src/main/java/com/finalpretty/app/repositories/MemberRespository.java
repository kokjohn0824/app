package com.finalpretty.app.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.finalpretty.app.model.Member;

public interface MemberRespository extends JpaRepository<Member, Integer> {

    // 後台編輯的修改
    @Transactional
    @Modifying
    @Query(value = "update member set nickname=:nickname, gender=:gender, age=:age, height=:height, weight=:weight, bodyFat=:bodyFat, visceralFat=:visceralFat, muscleMass=:muscleMass, becomeVIP=:becomeVIP where member_id=:member_id", nativeQuery = true)
    void updateById(@Param("member_id") Integer member_id,
            @Param("nickname") String nickname,
            @Param("gender") Integer gender,
            @Param("age") Integer age,
            @Param("height") double height,
            @Param("weight") double weight,
            @Param("bodyFat") double bodyFat,
            @Param("visceralFat") double visceralFat,
            @Param("muscleMass") double muscleMass,
            @Param("becomeVIP") Integer becomeVIP);

    // 前台找ID
    @Transactional
    @Modifying
    @Query(value = "select * from member where member_id=:member_id", nativeQuery = true)
    List<Member> findListById(@Param("member_id") Integer member_id);

    // 後台所有會員分頁
    // @Query("select u from Member u")
    // Page<Member> findList(Pageable pageable);

    // Page<Member> getMemberList(Integer pageNum, Integer pageSize);

}
