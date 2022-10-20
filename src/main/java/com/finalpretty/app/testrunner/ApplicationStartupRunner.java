package com.finalpretty.app.testrunner;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.finalpretty.app.model.Article;
import com.finalpretty.app.model.Member;
import com.finalpretty.app.repositories.MemberRespository;
import com.finalpretty.app.repositories.ArticleRespository;

@Component
public class ApplicationStartupRunner implements CommandLineRunner {

    @Autowired
    private MemberRespository memberRespository;

    @Autowired
    private ArticleRespository articleRespository;

    protected final Log logger = LogFactory.getLog(getClass());

    /* (non-Javadoc)
     * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
     */
    @Override
    public void run(String... args) throws Exception {

        logger.info("測試程式碼開始...");
        logger.info("請注意，run()內的程式碼報錯時將結束springboot");
        // 塞入測試資料
        Member m1 = new Member();
        m1.setGender(1);
        m1.setAge(18);
        m1.setHeight(170.1);
        m1.setWeight(65);
        m1.setBodyFat(10.3);
        m1.setVisceralFat(7.1);
        m1.setMuscleMass(20.4);
        m1.setBecomeVIP(1);
        memberRespository.save(m1);

        Member m2 = new Member();
        m2.setGender(0);
        m2.setAge(16);
        m2.setHeight(165.5);
        m2.setWeight(40);
        m2.setBodyFat(9.3);
        m2.setVisceralFat(8.1);
        m2.setMuscleMass(10.4);
        m2.setBecomeVIP(0);
        memberRespository.save(m2);


    }

}
