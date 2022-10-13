package com.finalpretty.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalpretty.app.model.Post;

public interface PostRespository extends JpaRepository<Post, Long> {
    
}
