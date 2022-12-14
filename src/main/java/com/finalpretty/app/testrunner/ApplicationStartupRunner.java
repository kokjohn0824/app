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
import com.finalpretty.app.model.DailyRecord;
import com.finalpretty.app.model.Food;
import com.finalpretty.app.model.Member;
import com.finalpretty.app.model.Product;
import com.finalpretty.app.model.Sports;
import com.finalpretty.app.model.Users;
import com.finalpretty.app.model.Video;
import com.finalpretty.app.repositories.ArticleRespository;
import com.finalpretty.app.repositories.DailyRecordRespository;
import com.finalpretty.app.repositories.FoodDailyRespository;
import com.finalpretty.app.repositories.FoodRespository;
import com.finalpretty.app.repositories.MemberRespository;
import com.finalpretty.app.repositories.OrderRespository;
import com.finalpretty.app.repositories.Order_detailRespository;
import com.finalpretty.app.repositories.ProductRespository;
import com.finalpretty.app.repositories.SportsDailyRespository;
import com.finalpretty.app.repositories.SportsRespository;
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
        private FoodRespository foodRespository;

        @Autowired
        private FoodDailyRespository foodDailyRespository;

        @Autowired
        private SportsRespository sportsRespository;

        @Autowired
        private SportsDailyRespository sportsDailyRespository;

        @Autowired
        private OrderRespository oDao;

        @Autowired
        private Order_detailRespository dtaDao;

        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;

        @Autowired
        private DailyRecordRespository dailyRecordRespository;

        protected final Log logger = LogFactory.getLog(getClass());

        /*
         * (non-Javadoc)
         * 
         * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
         */
        @Override
        public void run(String... args) throws Exception {

                logger.info("?????????????????????...");
                logger.info("????????????run()?????????????????????????????????springboot");

                try {
                        // ??????????????????
                        byte[] head1 = Files.readAllBytes(Paths.get("src/main/resources/static/img/member/head1.png"));
                        Member m1 = new Member();
                        m1.setGender(1);
                        m1.setAge(18);
                        m1.setHeight(170.1);
                        m1.setWeight(65);
                        m1.setBodyFat(10.3);
                        m1.setVisceralFat(7.1);
                        m1.setMuscleMass(20.4);
                        m1.setBecomeVIP(1);
                        m1.setNickname("???????????????");
                        m1.setPhoto(head1);
                        // memberRespository.save(m1);

                        byte[] head2 = Files.readAllBytes(Paths.get("src/main/resources/static/img/member/head2.png"));
                        Member m2 = new Member();
                        m2.setGender(0);
                        m2.setAge(16);
                        m2.setHeight(165.5);
                        m2.setWeight(40);
                        m2.setBodyFat(9.3);
                        m2.setVisceralFat(8.1);
                        m2.setMuscleMass(10.4);
                        m2.setBecomeVIP(0);
                        m2.setNickname("two");
                        m2.setPhoto(head2);

                        // ????????????
                        String encodedPassword;
                        Users users = new Users("alex", "bbbblf3@gmail.com", "asds", UserRole.USER);
                        users.setNickname("??????");
                        encodedPassword = bCryptPasswordEncoder.encode(users.getPassword());
                        users.setPassword((encodedPassword));
                        users.setEnabled(true);
                        users.setFkMember(m2);
                        // memberRespository.save(m2);
                        usersRepository.save(users);

                        Users user3 = new Users("z", "z@gmail.com", "123", UserRole.USER);
                        user3.setNickname("???????????????");
                        encodedPassword = bCryptPasswordEncoder.encode(user3.getPassword());
                        user3.setPassword((encodedPassword));
                        user3.setEnabled(true);
                        user3.setFkMember(m1);
                        usersRepository.save(user3);

                        // ??????admin??????
                        Users admin = new Users("sa", "root@gmail.com", "123", UserRole.ADMIN);
                        admin.setNickname("???????????????");
                        logger.info("admin password: " + admin.getPassword());
                        encodedPassword = bCryptPasswordEncoder.encode(admin.getPassword());
                        admin.setPassword((encodedPassword));
                        admin.setEnabled(true);
                        // memberRespository.save(m2);
                        usersRepository.save(admin);

                        // ????????????
                        byte[] foodP1 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/food/rice.jpg"));
                        Food food1 = new Food();
                        food1.setFoodname("??????");
                        food1.setCalorie(116);
                        food1.setPicture(foodP1);
                        foodRespository.save(food1);

                        byte[] foodP2 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/food/corn.jpg"));
                        Food food2 = new Food();
                        food2.setFoodname("??????");
                        food2.setCalorie(106);
                        food2.setPicture(foodP2);
                        foodRespository.save(food2);

                        byte[] foodP3 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/food/rice.jpg"));
                        Food food3 = new Food();
                        food3.setFoodname("??????");
                        food3.setCalorie(144);
                        food3.setPicture(foodP3);
                        foodRespository.save(food3);

                        byte[] foodP4 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/food/rice.jpg"));
                        Food food4 = new Food();
                        food4.setFoodname("??????");
                        food4.setCalorie(143);
                        food4.setPicture(foodP4);
                        foodRespository.save(food4);

                        byte[] foodP5 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/food/rice.jpg"));
                        Food food5 = new Food();
                        food5.setFoodname("??????");
                        food5.setCalorie(54);
                        food5.setPicture(foodP5);
                        foodRespository.save(food5);

                        // ????????????
                        byte[] sportsP1 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/sports/badminton.jpg"));
                        Sports sports1 = new Sports();
                        sports1.setSportsname("??????");
                        sports1.setCalorie(5);
                        sports1.setPicture(sportsP1);
                        sportsRespository.save(sports1);

                        byte[] sportsP2 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/sports/jog.jpg"));
                        Sports sports2 = new Sports();
                        sports2.setSportsname("??????");
                        sports2.setCalorie(8);
                        sports2.setPicture(sportsP2);
                        sportsRespository.save(sports2);

                        byte[] sportsP3 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/sports/swim.jpg"));
                        Sports sports3 = new Sports();
                        sports3.setSportsname("??????");
                        sports3.setCalorie(10);
                        sports3.setPicture(sportsP3);
                        sportsRespository.save(sports3);

                        byte[] sportsP4 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/sports/swim.jpg"));
                        Sports sports4 = new Sports();
                        sports4.setSportsname("??????");
                        sports4.setCalorie(8);
                        sports4.setPicture(sportsP4);
                        sportsRespository.save(sports4);

                        byte[] sportsP5 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/img/sports/swim.jpg"));
                        Sports sports5 = new Sports();
                        sports5.setSportsname("?????????");
                        sports5.setCalorie(9);
                        sports5.setPicture(sportsP5);
                        sportsRespository.save(sports5);

                        // ????????????
                        byte[] video1 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/public/video/pectoral1.jpg"));
                        Video v1 = new Video();
                        v1.setTitle("???????????????");
                        v1.setType("??????");
                        v1.setBody_parts("?????????");
                        v1.setPicture(video1);
                        v1.setViews(20);
                        v1.setUrl("pectoral1.mp4");
                        videoRespository.save(v1);

                        byte[] video2 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/public/video/pectoral2.jpg"));
                        Video v2 = new Video();
                        v2.setTitle("???????????????");
                        v2.setType("??????");
                        v2.setBody_parts("?????????");
                        v2.setPicture(video2);
                        v2.setViews(11);
                        v2.setUrl("pectoral2.mp4");
                        videoRespository.save(v2);

                        byte[] video3 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/public/video/pectoral3.jpg"));
                        Video v3 = new Video();
                        v3.setTitle("???????????????");
                        v3.setType("??????");
                        v3.setBody_parts("?????????");
                        v3.setPicture(video3);
                        v3.setViews(7);
                        v3.setUrl("pectoral3.mp4");
                        videoRespository.save(v3);

                        byte[] video4 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/public/video/pectoral4.jpg"));
                        Video v4 = new Video();
                        v4.setTitle("???????????????");
                        v4.setType("??????");
                        v4.setBody_parts("?????????");
                        v4.setPicture(video4);
                        v4.setViews(12);
                        v4.setUrl("pectoral4.mp4");
                        videoRespository.save(v4);

                        byte[] video5 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/public/video/backMuscles1.jpg"));
                        Video v5 = new Video();
                        v5.setTitle("????????????");
                        v5.setType("??????");
                        v5.setBody_parts("?????????");
                        v5.setPicture(video5);
                        v5.setViews(20);
                        v5.setUrl("backMuscles1.mp4");
                        videoRespository.save(v5);

                        byte[] video6 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/public/video/backMuscles2.jpg"));
                        Video v6 = new Video();
                        v6.setTitle("????????????");
                        v6.setType("??????");
                        v6.setBody_parts("?????????");
                        v6.setPicture(video6);
                        v6.setViews(20);
                        v6.setUrl("backMuscles2.mp4");
                        videoRespository.save(v6);

                        byte[] video7 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/public/video/leg1.jpg"));
                        Video v7 = new Video();
                        v7.setTitle("??????????????????");
                        v7.setType("??????");
                        v7.setBody_parts("????????????");
                        v7.setPicture(video7);
                        v7.setViews(11);
                        v7.setUrl("leg1.mp4");
                        videoRespository.save(v7);

                        byte[] video8 = Files
                                        .readAllBytes(Paths.get(
                                                        "src/main/resources/static/public/video/leg2.jpg"));
                        Video v8 = new Video();
                        v8.setTitle("????????????");
                        v8.setType("??????");
                        v8.setBody_parts("????????????");
                        v8.setPicture(video8);
                        v8.setViews(20);
                        v8.setUrl("leg2.mp4");
                        videoRespository.save(v8);

                        // ????????????
                        Path text1 = Paths.get("src/main/resources/static/img/article/article1.txt");
                        String content1 = Files.readString(text1);
                        byte[] article1 = Files
                                        .readAllBytes(Paths.get("src/main/resources/static/img/article/article1.png"));
                        Article a1 = new Article();
                        a1.setTitle("??????????????????????????????????????????");
                        a1.setText(content1);
                        a1.setPicture(article1);
                        a1.setType("????????????");
                        a1.setViews(1);
                        articleRespository.save(a1);

                        Path text2 = Paths.get("src/main/resources/static/img/article/article2.txt");
                        String content2 = Files.readString(text2);
                        byte[] article2 = Files
                                        .readAllBytes(Paths.get("src/main/resources/static/img/article/article2.png"));
                        Article a2 = new Article();
                        a2.setTitle("???????????????????????????????????????????????????");
                        a2.setText(content2);
                        a2.setPicture(article2);
                        a2.setType("????????????");
                        a2.setViews(2);
                        articleRespository.save(a2);

                        Path text3 = Paths.get("src/main/resources/static/img/article/article3.txt");
                        String content3 = Files.readString(text3);
                        byte[] article3 = Files
                                        .readAllBytes(Paths.get("src/main/resources/static/img/article/article3.png"));
                        Article a3 = new Article();
                        a3.setTitle("????????????????????????????????????");
                        a3.setText(content3);
                        a3.setPicture(article3);
                        a3.setType("????????????");
                        a3.setViews(3);
                        articleRespository.save(a3);

                        Path text4 = Paths.get("src/main/resources/static/img/article/article4.txt");
                        String content4 = Files.readString(text4);
                        byte[] article4 = Files
                                        .readAllBytes(Paths.get("src/main/resources/static/img/article/article4.png"));
                        Article a4 = new Article();
                        a4.setTitle("?????????????????????????????????");
                        a4.setText(content4);
                        a4.setPicture(article4);
                        a4.setType("????????????");
                        a4.setViews(4);
                        articleRespository.save(a4);

                        Path text5 = Paths.get("src/main/resources/static/img/article/article5.txt");
                        String content5 = Files.readString(text5);
                        byte[] article5 = Files
                                        .readAllBytes(Paths.get("src/main/resources/static/img/article/article5.png"));
                        Article a5 = new Article();
                        a5.setTitle("?????????????????????????????????");
                        a5.setText(content5);
                        a5.setPicture(article5);
                        a5.setType("????????????");
                        a5.setViews(5);
                        articleRespository.save(a5);

                        Path text6 = Paths.get("src/main/resources/static/img/article/article6.txt");
                        String content6 = Files.readString(text6);
                        byte[] article6 = Files
                                        .readAllBytes(Paths.get("src/main/resources/static/img/article/article6.png"));
                        Article a6 = new Article();
                        a6.setTitle("??????????????? ?????????????????????");
                        a6.setText(content6);
                        a6.setPicture(article6);
                        a6.setType("????????????");
                        a6.setViews(6);
                        articleRespository.save(a6);

                        Path text7 = Paths.get("src/main/resources/static/img/article/article7.txt");
                        String content7 = Files.readString(text7);
                        byte[] article7 = Files
                                        .readAllBytes(Paths.get("src/main/resources/static/img/article/article7.png"));
                        Article a7 = new Article();
                        a7.setTitle("?????????????????????????????????????????????");
                        a7.setText(content7);
                        a7.setPicture(article7);
                        a7.setType("????????????");
                        a7.setViews(7);
                        articleRespository.save(a7);

                        Path text8 = Paths.get("src/main/resources/static/img/article/article8.txt");
                        String content8 = Files.readString(text8);
                        byte[] article8 = Files
                                        .readAllBytes(Paths.get("src/main/resources/static/img/article/article8.png"));
                        Article a8 = new Article();
                        a8.setTitle("???????????????????????? 3??????????????????????????????");
                        a8.setText(content8);
                        a8.setPicture(article8);
                        a8.setType("????????????");
                        a8.setViews(8);
                        articleRespository.save(a8);

                        Path text9 = Paths.get("src/main/resources/static/img/article/article9.txt");
                        String content9 = Files.readString(text9);
                        byte[] article9 = Files
                                        .readAllBytes(Paths.get("src/main/resources/static/img/article/article9.png"));
                        Article a9 = new Article();
                        a9.setTitle("????????????????????????????????????");
                        a9.setText(content9);
                        a9.setPicture(article9);
                        a9.setType("????????????");
                        a9.setViews(9);
                        articleRespository.save(a9);

                        Path text10 = Paths.get("src/main/resources/static/img/article/article10.txt");
                        String content10 = Files.readString(text10);
                        byte[] article10 = Files
                                        .readAllBytes(Paths.get("src/main/resources/static/img/article/article10.png"));
                        Article a10 = new Article();
                        a10.setTitle("????????????????????????????????????");
                        a10.setText(content10);
                        a10.setPicture(article10);
                        a10.setType("????????????");
                        a10.setViews(10);
                        articleRespository.save(a10);

                        Path text11 = Paths.get("src/main/resources/static/img/article/article11.txt");
                        String content11 = Files.readString(text11);
                        byte[] article11 = Files
                                        .readAllBytes(Paths.get("src/main/resources/static/img/article/article11.png"));
                        Article a11 = new Article();
                        a11.setTitle("????????????????????????~????????????");
                        a11.setText(content11);
                        a11.setPicture(article11);
                        a11.setType("????????????");
                        a11.setViews(11);
                        articleRespository.save(a11);

                        Path text12 = Paths.get("src/main/resources/static/img/article/article12.txt");
                        String content12 = Files.readString(text12);
                        byte[] article12 = Files
                                        .readAllBytes(Paths.get("src/main/resources/static/img/article/article12.png"));
                        Article a12 = new Article();
                        a12.setTitle("????????????????????????????????????");
                        a12.setText(content12);
                        a12.setPicture(article12);
                        a12.setType("????????????");
                        a12.setViews(12);
                        articleRespository.save(a12);

                        Path text13 = Paths.get("src/main/resources/static/img/article/article13.txt");
                        String content13 = Files.readString(text13);
                        byte[] article13 = Files
                                        .readAllBytes(Paths.get("src/main/resources/static/img/article/article13.png"));
                        Article a13 = new Article();
                        a13.setTitle("???????????????????????????????????????????????????????????????");
                        a13.setText(content13);
                        a13.setPicture(article13);
                        a13.setType("????????????");
                        a13.setViews(13);
                        articleRespository.save(a13);

                        // ??????
                        Product product = new Product();
                        product.setTitle("????????????????????????");
                        product.setType("????????????");
                        product.setPrice(840);
                        product.setStock(500);
                        product.setText("?????????????????????????????????200mg?????????");
                        product.setOnsale(1);
                        product.setVolume(0);
                        String saveFiledir = System.getProperty("user.dir")
                                        + "/src/main/resources/static/img/product/p1.jpg";
                        File saveFilePath = new File(saveFiledir);
                        product.setPicture(Files.readAllBytes(saveFilePath.toPath()));
                        pDao.save(product);

                        Product product2 = new Product();
                        product2.setTitle("???????????????????????????");
                        product2.setType("????????????");
                        product2.setPrice(450);
                        product2.setStock(50);
                        product2.setText("?????????????????????????????????????????????");
                        product2.setOnsale(1);
                        product2.setVolume(0);
                        String saveFiledir2 = System.getProperty("user.dir")
                                        + "/src/main/resources/static/img/product/p2.jpg";
                        File saveFilePath2 = new File(saveFiledir2);
                        product2.setPicture(Files.readAllBytes(saveFilePath2.toPath()));
                        pDao.save(product2);

                        Product product3 = new Product();
                        product3.setTitle("??????????????????????????????");
                        product3.setType("????????????");
                        product3.setPrice(789);
                        product3.setStock(50);
                        product3.setText("???????????????????????????????????????B???");
                        product3.setOnsale(1);
                        product3.setVolume(0);
                        String saveFiledir3 = System.getProperty("user.dir")
                                        + "/src/main/resources/static/img/product/p3.jpg";
                        File saveFilePath3 = new File(saveFiledir3);
                        product3.setPicture(Files.readAllBytes(saveFilePath3.toPath()));
                        pDao.save(product3);

                        Product product4 = new Product();
                        product4.setTitle("IVENOR_???????????????");
                        product4.setType("????????????");
                        product4.setPrice(1080);
                        product4.setStock(50);
                        product4.setText("???????????? ????????????");
                        product4.setOnsale(1);
                        product4.setVolume(0);
                        String saveFiledir4 = System.getProperty("user.dir")
                                        + "/src/main/resources/static/img/product/p4.jpg";
                        File saveFilePath4 = new File(saveFiledir4);
                        product4.setPicture(Files.readAllBytes(saveFilePath4.toPath()));
                        pDao.save(product4);

                        Product product5 = new Product();
                        product5.setTitle("???????????????");
                        product5.setType("????????????");
                        product5.setPrice(419);
                        product5.setStock(50);
                        product5.setText("??????????????????????????????");
                        product5.setOnsale(1);
                        product5.setVolume(0);
                        String saveFiledir5 = System.getProperty("user.dir")
                                        + "/src/main/resources/static/img/product/p5.jpg";
                        File saveFilePath5 = new File(saveFiledir5);
                        product5.setPicture(Files.readAllBytes(saveFilePath5.toPath()));
                        pDao.save(product5);

                        Product product6 = new Product();
                        product6.setTitle("DHC????????????");
                        product6.setType("????????????");
                        product6.setPrice(356);
                        product6.setStock(0);
                        product6.setText("??????DHC?????????????????????");
                        product6.setOnsale(1);
                        product6.setVolume(0);
                        String saveFiledir6 = System.getProperty("user.dir")
                                        + "/src/main/resources/static/img/product/p6.jpg";
                        File saveFilePath6 = new File(saveFiledir6);
                        product6.setPicture(Files.readAllBytes(saveFilePath6.toPath()));
                        pDao.save(product6);

                        Product product7 = new Product();
                        product7.setTitle("ADISI?????????");
                        product7.setType("????????????");
                        product7.setPrice(880);
                        product7.setStock(50);
                        product7.setText("??????????????????????????????????????????????????????");
                        product7.setOnsale(1);
                        product7.setVolume(0);
                        String saveFiledir7 = System.getProperty("user.dir") +
                                        "/src/main/resources/static/img/product/p7.jpg";
                        File saveFilePath7 = new File(saveFiledir7);
                        product7.setPicture(Files.readAllBytes(saveFilePath7.toPath()));
                        pDao.save(product7);

                        Product product8 = new Product();
                        product8.setTitle("?????????????????????");
                        product8.setType("????????????");
                        product8.setPrice(611);
                        product8.setStock(0);
                        product8.setText("???????????????1?????????");
                        product8.setOnsale(1);
                        product8.setVolume(0);
                        String saveFiledir8 = System.getProperty("user.dir") +
                                        "/src/main/resources/static/img/product/p8.jpg";
                        File saveFilePath8 = new File(saveFiledir8);
                        product8.setPicture(Files.readAllBytes(saveFilePath8.toPath()));
                        pDao.save(product8);

                        Product product9 = new Product();
                        product9.setTitle("Adidas Strength ????????????????????????");
                        product9.setType("????????????");
                        product9.setPrice(790);
                        product9.setStock(2);
                        product9.setText("??????????????????????????????????????????????????????");
                        product9.setOnsale(1);
                        product9.setVolume(0);
                        String saveFiledir9 = System.getProperty("user.dir") +
                                        "/src/main/resources/static/img/product/p9.jpg";
                        File saveFilePath9 = new File(saveFiledir9);
                        product9.setPicture(Files.readAllBytes(saveFilePath9.toPath()));
                        pDao.save(product9);

                        Product product10 = new Product();
                        product10.setTitle("?????????????????????");
                        product10.setType("????????????");
                        product10.setPrice(590);
                        product10.setStock(1);
                        product10.setText("??????????????????,?????????????????????,????????????????????????");
                        product10.setOnsale(1);
                        product10.setVolume(0);
                        String saveFiledir10 = System.getProperty("user.dir") +
                                        "/src/main/resources/static/img/product/p10.jpg";
                        File saveFilePath10 = new File(saveFiledir10);
                        product10.setPicture(Files.readAllBytes(saveFilePath10.toPath()));
                        pDao.save(product10);

                        Product product11 = new Product();
                        product11.setTitle("???????????????????????????");
                        product11.setType("????????????");
                        product11.setPrice(930);
                        product11.setStock(10);
                        product11.setText("???????????????????????????,?????????????????????????????????");
                        product11.setOnsale(1);
                        product11.setVolume(0);
                        String saveFiledir11 = System.getProperty("user.dir") +
                                        "/src/main/resources/static/img/product/p11.jpg";
                        File saveFilePath11 = new File(saveFiledir11);
                        product11.setPicture(Files.readAllBytes(saveFilePath11.toPath()));
                        pDao.save(product11);

                        Product product12 = new Product();
                        product12.setTitle("?????????????????????????????????");
                        product12.setType("????????????");
                        product12.setPrice(490);
                        product12.setStock(3);
                        product12.setText("???????????????????????????");
                        product12.setOnsale(1);
                        product12.setVolume(0);
                        String saveFiledir12 = System.getProperty("user.dir") +
                                        "/src/main/resources/static/img/product/p12.jpg";
                        File saveFilePath12 = new File(saveFiledir12);
                        product12.setPicture(Files.readAllBytes(saveFilePath12.toPath()));
                        pDao.save(product12);

                        Product product13 = new Product();
                        product13.setTitle("?????????????????????????????????60ml");
                        product13.setType("????????????");
                        product13.setPrice(399);
                        product13.setStock(50);
                        product13.setText("????????????????????? GOT???GPT ???");
                        product13.setOnsale(1);
                        product13.setVolume(0);
                        String saveFiledir13 = System.getProperty("user.dir") +
                                        "/src/main/resources/static/img/product/p13.jpg";
                        File saveFilePath13 = new File(saveFiledir13);
                        product13.setPicture(Files.readAllBytes(saveFilePath13.toPath()));
                        pDao.save(product13);

                        Product product14 = new Product();
                        product14.setTitle("????????????????????????(100???/???)");
                        product14.setType("????????????");
                        product14.setPrice(1197);
                        product14.setStock(0);
                        product14.setText("???????????????9%?????????????????? ??????????????? ????????????????????????");
                        product14.setOnsale(0);
                        product14.setVolume(0);
                        String saveFiledir14 = System.getProperty("user.dir") +
                                        "/src/main/resources/static/img/product/p14.jpg";
                        File saveFilePath14 = new File(saveFiledir14);
                        product14.setPicture(Files.readAllBytes(saveFilePath14.toPath()));
                        pDao.save(product14);

                        Product product15 = new Product();
                        product15.setTitle("????????????????????????????????????????????????????????????C");
                        product15.setType("????????????");
                        product15.setPrice(290);
                        product15.setStock(2);
                        product15.setText("???????????????????????????????????????");
                        product15.setOnsale(0);
                        product15.setVolume(0);
                        String saveFiledir15 = System.getProperty("user.dir") +
                                        "/src/main/resources/static/img/product/p15.jpg";
                        File saveFilePath15 = new File(saveFiledir15);
                        product15.setPicture(Files.readAllBytes(saveFilePath15.toPath()));
                        pDao.save(product15);

                        // ????????????
                        DailyRecord d1030 = new DailyRecord();
                        d1030.setDate_time("2022/10/30");
                        d1030.setWeight(48);
                        d1030.setBodyFat(17);
                        d1030.setDrinkingWater(2300);
                        d1030.setMembers(m1);
                        dailyRecordRespository.save(d1030);

                        DailyRecord d1031 = new DailyRecord();
                        d1031.setDate_time("2022/10/31");
                        d1031.setWeight(47);
                        d1031.setBodyFat(16);
                        d1031.setDrinkingWater(1900);
                        d1031.setMembers(m1);
                        dailyRecordRespository.save(d1031);

                        DailyRecord d1101 = new DailyRecord();
                        d1101.setDate_time("2022/11/01");
                        d1101.setWeight(50);
                        d1101.setBodyFat(19);
                        d1101.setDrinkingWater(2300);
                        d1101.setMembers(m1);
                        dailyRecordRespository.save(d1101);

                        DailyRecord d1102 = new DailyRecord();
                        d1102.setDate_time("2022/11/02");
                        d1102.setWeight(53);
                        d1102.setBodyFat(20);
                        d1102.setDrinkingWater(2500);
                        d1102.setMembers(m1);
                        dailyRecordRespository.save(d1102);

                        DailyRecord d1103 = new DailyRecord();
                        d1103.setDate_time("2022/11/03");
                        d1103.setWeight(49);
                        d1103.setBodyFat(18);
                        d1103.setDrinkingWater(2000);
                        d1103.setMembers(m1);
                        dailyRecordRespository.save(d1103);

                        DailyRecord d1104 = new DailyRecord();
                        d1104.setDate_time("2022/11/04");
                        d1104.setWeight(43);
                        d1104.setBodyFat(14);
                        d1104.setDrinkingWater(1800);
                        d1104.setMembers(m1);
                        dailyRecordRespository.save(d1104);

                        DailyRecord d1105 = new DailyRecord();
                        d1105.setDate_time("2022/11/05");
                        d1105.setWeight(48);
                        d1105.setBodyFat(17);
                        d1105.setDrinkingWater(2000);
                        d1105.setMembers(m1);
                        dailyRecordRespository.save(d1105);

                        DailyRecord d1106 = new DailyRecord();
                        d1106.setDate_time("2022/11/06");
                        d1106.setWeight(50);
                        d1106.setBodyFat(12);
                        d1106.setDrinkingWater(1500);
                        d1106.setMembers(m1);
                        dailyRecordRespository.save(d1106);

                        DailyRecord d1107 = new DailyRecord();
                        d1107.setDate_time("2022/11/07");
                        d1107.setWeight(53);
                        d1107.setBodyFat(14);
                        d1107.setDrinkingWater(1000);
                        d1107.setMembers(m1);
                        dailyRecordRespository.save(d1107);

                        logger.info("??????????????????");
                } catch (Exception e) {
                        logger.info(e.getMessage());
                        e.printStackTrace();
                }
                logger.info("??????ApplicationRunner");

        }

}