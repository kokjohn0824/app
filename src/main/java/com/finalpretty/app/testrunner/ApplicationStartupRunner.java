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
                                .readAllBytes(Paths.get(
                                                "src/main/resources/static/video/939eaf7939f3495ebab182315ff76849.jpg"));
                Video v1 = new Video();
                v1.setTitle("四足俯臥撑");
                v1.setType("胸肌");
                v1.setBody_parts("胸大肌");
                v1.setPicture(video1);
                v1.setUrl("3db97465e2684771a6bce945645c9d00.mp4");
                videoRespository.save(v1);

                // 插入文章
                Path text1 = Paths.get("src/main/resources/static/img/article/article1.txt");
                String content1 = Files.readString(text1);
                byte[] article1 = Files
                                .readAllBytes(Paths.get("src/main/resources/static/img/article/article1.png"));
                Article a1 = new Article();
                a1.setTitle("女孩必看！生理週期的飲食方針");
                a1.setText(content1);
                a1.setPicture(article1);
                articleRespository.save(a1);

                Path text2 = Paths.get("src/main/resources/static/img/article/article2.txt");
                String content2 = Files.readString(text2);
                byte[] article2 = Files
                                .readAllBytes(Paths.get("src/main/resources/static/img/article/article2.png"));
                Article a2 = new Article();
                a2.setTitle("手腳冰冷分三類！中醫食補法溫熱手腳");
                a2.setText(content2);
                a2.setPicture(article2);
                articleRespository.save(a2);

                Path text3 = Paths.get("src/main/resources/static/img/article/article3.txt");
                String content3 = Files.readString(text3);
                byte[] article3 = Files
                                .readAllBytes(Paths.get("src/main/resources/static/img/article/article3.png"));
                Article a3 = new Article();
                a3.setTitle("便秘不硬擠，順暢四技巧！");
                a3.setText(content3);
                a3.setPicture(article3);
                articleRespository.save(a3);

                Path text4 = Paths.get("src/main/resources/static/img/article/article4.txt");
                String content4 = Files.readString(text4);
                byte[] article4 = Files
                                .readAllBytes(Paths.get("src/main/resources/static/img/article/article4.png"));
                Article a4 = new Article();
                a4.setTitle("消水腫的祕密武器 食物篇");
                a4.setText(content4);
                a4.setPicture(article4);
                articleRespository.save(a4);

                Path text5 = Paths.get("src/main/resources/static/img/article/article5.txt");
                String content5 = Files.readString(text5);
                byte[] article5 = Files
                                .readAllBytes(Paths.get("src/main/resources/static/img/article/article5.png"));
                Article a5 = new Article();
                a5.setTitle("舒緩經痛不發胖的好食材");
                a5.setText(content5);
                a5.setPicture(article5);
                articleRespository.save(a5);

                Path text6 = Paths.get("src/main/resources/static/img/article/article6.txt");
                String content6 = Files.readString(text6);
                byte[] article6 = Files
                                .readAllBytes(Paths.get("src/main/resources/static/img/article/article6.png"));
                Article a6 = new Article();
                a6.setTitle("瘦身不瘦胸 美胸十全大補帖");
                a6.setText(content6);
                a6.setPicture(article6);
                articleRespository.save(a6);

                Path text7 = Paths.get("src/main/resources/static/img/article/article7.txt");
                String content7 = Files.readString(text7);
                byte[] article7 = Files
                                .readAllBytes(Paths.get("src/main/resources/static/img/article/article7.png"));
                Article a7 = new Article();
                a7.setTitle("吃不吃天人交戰？蛋糕免發胖守則");
                a7.setText(content7);
                a7.setPicture(article7);
                articleRespository.save(a7);

                Path text8 = Paths.get("src/main/resources/static/img/article/article8.txt");
                String content8 = Files.readString(text8);
                byte[] article8 = Files
                                .readAllBytes(Paths.get("src/main/resources/static/img/article/article8.png"));
                Article a8 = new Article();
                a8.setTitle("工作久站腳好痠？ 3撇步告別腫脹蘿蔔腿！");
                a8.setText(content8);
                a8.setPicture(article8);
                articleRespository.save(a8);

        }

}
