package com.finalpretty.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.finalpretty.app.model.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer>{
    
    
    Photo findByPath(String path);
}
