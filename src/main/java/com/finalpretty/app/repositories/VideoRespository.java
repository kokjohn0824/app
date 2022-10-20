package com.finalpretty.app.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import com.finalpretty.app.model.Video;

public interface VideoRespository extends JpaRepository<Video, Integer> {

    @Modifying
    @Query(value = "select * from video where type=:type", nativeQuery = true)
    List<Video> findByType(@Param("type") String type);

}
