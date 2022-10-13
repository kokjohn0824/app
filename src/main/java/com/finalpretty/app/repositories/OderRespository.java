package com.finalpretty.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.finalpretty.app.model.Order;

public interface OderRespository  extends JpaRepository< Order, Long>{
    
}
