package com.finalpretty.app.testrunner;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.finalpretty.app.model.Article;
import com.finalpretty.app.model.Member;
import com.finalpretty.app.model.Video;
import com.finalpretty.app.repositories.ArticleRespository;
import com.finalpretty.app.repositories.MemberRespository;
import com.finalpretty.app.repositories.VideoRespository;

@Component
public class ApplicationStartupRunner implements CommandLineRunner {

    @Autowired
    private MemberRespository memberRespository;

    @Autowired
    private ArticleRespository articleRespository;

    @Autowired
    private VideoRespository videoRespository;

    protected final Log logger = LogFactory.getLog(getClass());

    /*
     * (non-Javadoc)
     * 
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

        // 插入影片
        byte[] video1 = Files
                .readAllBytes(Paths.get("src/main/resources/static/video/939eaf7939f3495ebab182315ff76849.jpg"));
        Video v1 = new Video();
        v1.setTitle("俯臥撐");
        v1.setType("胸肌");
        v1.setBody_parts("胸部肌群");
        v1.setPicture(video1);
        v1.setUrl("3db97465e2684771a6bce945645c9d00.mp4");
        videoRespository.save(v1);

        // 插入文章
        Path text1 = Paths.get("src/main/resources/static/img/article/article1.txt");
        String content = Files.readString(text1);
        byte[] article1 = Files
                .readAllBytes(Paths.get("src/main/resources/static/img/article/article1.png"));
        Article a1 = new Article();
        a1.setTitle("女孩必看！生理週期的飲食方針");
        a1.setText(content);
        a1.setPicture(article1);
        articleRespository.save(a1);

    }

}
