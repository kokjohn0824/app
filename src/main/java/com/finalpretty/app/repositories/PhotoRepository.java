package com.finalpretty.app.repositories;

import org.springframework.data.jpa.repository.*;

import com.finalpretty.app.model.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer>{
    
    
    Photo findByPath(String path);
}
