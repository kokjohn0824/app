package com.finalpretty.app.testrunner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.finalpretty.app.model.Member;
import com.finalpretty.app.repositories.MemberRespository;

import ch.qos.logback.classic.Logger;

@Component
public class ApplicationStartupRunner implements CommandLineRunner{

    @Autowired
    private MemberRespository memberRespository;
    
    protected final Log logger = LogFactory.getLog(getClass());


    @Override
    public void run(String... args) throws Exception {
        
        logger.info("測試程式碼開始...");
        logger.info("請注意，run()內的程式碼報錯時將結束springboot");
        //塞入測試資料
        Member m  = new Member();
        m.setAge(18);
        m.setBecomeVIP(0);
        m.setGender("Male");
        m.setHeight(170.1f);
        memberRespository.save(m);
        
        
        logger.info("結束測試程式碼");
    

    }
    
}
