package com.finalpretty.app.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import com.finalpretty.app.model.Video;

public interface VideoRespository extends JpaRepository<Video, Integer> {

    @Modifying
    @Query(value = "select * from video where type=:type order by video_id desc", nativeQuery = true)
    List<Video> findByType(@Param("type") String type);

    @Transactional
    @Modifying
    @Query(value = "update video set title=:title, type=:type, body_parts=:body_parts, picture=:picture where video_id=:video_id", nativeQuery = true)
    void updateById(@Param("video_id") Integer video_id,
            @Param("title") String title,
            @Param("type") String type,
            @Param("body_parts") String body_parts,
            @Param("picture") byte[] picture);

    @Transactional
    @Modifying
    @Query(value = "select * from video order by video_id desc", nativeQuery = true)
    List<Video> findAlloOrderById();

}
