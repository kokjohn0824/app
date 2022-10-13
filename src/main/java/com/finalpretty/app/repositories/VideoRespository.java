package com.finalpretty.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalpretty.app.model.Video;

public interface VideoRespository extends JpaRepository<Video, Long> {

}
