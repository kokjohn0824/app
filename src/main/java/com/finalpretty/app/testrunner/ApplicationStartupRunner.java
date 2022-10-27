package com.finalpretty.app.testrunner;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.finalpretty.app.model.Article;
import com.finalpretty.app.model.Member;
import com.finalpretty.app.model.Product;
import com.finalpretty.app.model.Users;
import com.finalpretty.app.model.Video;
import com.finalpretty.app.repositories.ArticleRespository;
import com.finalpretty.app.repositories.MemberRespository;
import com.finalpretty.app.repositories.OrderRespository;
import com.finalpretty.app.repositories.Order_detailRespository;
import com.finalpretty.app.repositories.ProductRespository;
import com.finalpretty.app.repositories.VideoRespository;
import com.finalpretty.app.security.UserRole;
import com.finalpretty.app.security.UsersRepository;

@Component
public class ApplicationStartupRunner implements CommandLineRunner {

        @Autowired
        private MemberRespository memberRespository;

        @Autowired
        private ArticleRespository articleRespository;

        @Autowired
        private VideoRespository videoRespository;

        @Autowired
        private UsersRepository usersRepository;

        @Autowired
        private ProductRespository pDao;

        @Autowired
        private OrderRespository oDao;

        @Autowired
        private Order_detailRespository dtaDao;

        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;

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

                // 插入帳號
                String encodedPassword;
                Users users = new Users("alex", "bbbblf3@gmail.com", "asds", UserRole.USER);
                users.setNickname("金尼");
                logger.info("users password: " + users.getPassword());
                encodedPassword = bCryptPasswordEncoder.encode(users.getPassword());
                users.setPassword((encodedPassword));
                users.setEnabled(true);
                users.setFkMember(m2);
                // memberRespository.save(m2);
                usersRepository.save(users);

                // 插入影片
                byte[] video1 = Files
                                .readAllBytes(Paths.get(
                                                "src/main/resources/static/video/939eaf7939f3495ebab182315ff76849.jpg"));
                Video v1 = new Video();
                v1.setTitle("四足俯臥撐");
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

                // 產品
                Product product = new Product();
                product.setTitle("雙速咖啡因雙層錠");
                product.setType("保健食品");
                product.setPrice(840);
                product.setStock(500);
                product.setText("專為耐力運動設計，每錠200mg咖啡因");
                product.setOnsale(0);
                String saveFiledir = System.getProperty("user.dir") + "/src/main/resources/static/img/product/p1.jpg";
                File saveFilePath = new File(saveFiledir);
                product.setPicture(Files.readAllBytes(saveFilePath.toPath()));
                pDao.save(product);

                Product product2 = new Product();
                product2.setTitle("義美生醫乳清蛋白飲");
                product2.setType("保健食品");
                product2.setPrice(450);
                product2.setStock(50);
                product2.setText("無添加香料、色素、甜味劑、奶精");
                product2.setOnsale(0);
                String saveFiledir2 = System.getProperty("user.dir") + "/src/main/resources/static/img/product/p2.jpg";
                File saveFilePath2 = new File(saveFiledir2);
                product2.setPicture(Files.readAllBytes(saveFilePath2.toPath()));
                pDao.save(product2);

                Product product3 = new Product();
                product3.setTitle("善存成人綜合維他命錠");
                product3.setType("保健食品");
                product3.setPrice(789);
                product3.setStock(50);
                product3.setText("特別添加番茄紅素、葉黃素、B群");
                product3.setOnsale(0);
                String saveFiledir3 = System.getProperty("user.dir") + "/src/main/resources/static/img/product/p3.jpg";
                File saveFilePath3 = new File(saveFiledir3);
                product3.setPicture(Files.readAllBytes(saveFilePath3.toPath()));
                pDao.save(product3);

                Product product4 = new Product();
                product4.setTitle("IVENOR_舒活關節霜");
                product4.setType("保健用品");
                product4.setPrice(1080);
                product4.setStock(50);
                product4.setText("直達筋骨 靈活好動");
                product4.setOnsale(0);
                String saveFiledir4 = System.getProperty("user.dir") + "/src/main/resources/static/img/product/p4.jpg";
                File saveFilePath4 = new File(saveFiledir4);
                product4.setPicture(Files.readAllBytes(saveFilePath4.toPath()));
                pDao.save(product4);

                Product product5 = new Product();
                product5.setTitle("朵麗輕纖錠");
                product5.setType("保健食品");
                product5.setPrice(419);
                product5.setStock(50);
                product5.setText("口感升級～多酚乳酸味");
                product5.setOnsale(0);
                String saveFiledir5 = System.getProperty("user.dir") + "/src/main/resources/static/img/product/p5.jpg";
                File saveFilePath5 = new File(saveFiledir5);
                product5.setPicture(Files.readAllBytes(saveFilePath5.toPath()));
                pDao.save(product5);

                Product product6 = new Product();
                product6.setTitle("DHC濃縮薑黃");
                product6.setType("保健食品");
                product6.setPrice(356);
                product6.setStock(50);
                product6.setText("台灣DHC直供．品質保證");
                product6.setOnsale(0);
                String saveFiledir6 = System.getProperty("user.dir") + "/src/main/resources/static/img/product/p6.jpg";
                File saveFilePath6 = new File(saveFiledir6);
                product6.setPicture(Files.readAllBytes(saveFilePath6.toPath()));
                pDao.save(product6);

        }

}
