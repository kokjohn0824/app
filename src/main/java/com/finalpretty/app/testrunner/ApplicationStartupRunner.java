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
import com.finalpretty.app.model.Food_daily;
import com.finalpretty.app.model.Member;
import com.finalpretty.app.model.Product;
import com.finalpretty.app.model.Sports;
import com.finalpretty.app.model.Sports_daily;
import com.finalpretty.app.model.Users;
import com.finalpretty.app.model.Video;
import com.finalpretty.app.repositories.ArticleRespository;
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
                m1.setNickname("tset");
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

                // 插入食物
                byte[] foodP1 = Files
                                .readAllBytes(Paths.get(
                                                "src/main/resources/static/img/food/rice.jpg"));
                Food food1 = new Food();
                food1.setFoodname("rice");
                food1.setCalorie(116);
                food1.setPicture(foodP1);
                foodRespository.save(food1);

                byte[] foodP2 = Files
                                .readAllBytes(Paths.get(
                                                "src/main/resources/static/img/food/corn.jpg"));
                Food food2 = new Food();
                food2.setFoodname("玉米");
                food2.setCalorie(106);
                food2.setPicture(foodP2);
                foodRespository.save(food2);

                // 插入運動
                byte[] sportsP1 = Files
                                .readAllBytes(Paths.get(
                                                "src/main/resources/static/img/sports/badminton.jpg"));
                Sports sports1 = new Sports();
                sports1.setSportsname("羽球");
                sports1.setCalorie(153);
                sports1.setPicture(sportsP1);
                sportsRespository.save(sports1);

                byte[] sportsP2 = Files
                                .readAllBytes(Paths.get(
                                                "src/main/resources/static/img/sports/jog.jpg"));
                Sports sports2 = new Sports();
                sports2.setSportsname("慢跑");
                sports2.setCalorie(246);
                sports2.setPicture(sportsP2);
                sportsRespository.save(sports2);

                byte[] sportsP3 = Files
                                .readAllBytes(Paths.get(
                                                "src/main/resources/static/img/sports/swim.jpg"));
                Sports sports3 = new Sports();
                sports3.setSportsname("游泳");
                sports3.setCalorie(300);
                sports3.setPicture(sportsP3);
                sportsRespository.save(sports3);

                // // 插入日記
                // DailyRecord dailyRecord1 = new DailyRecord();
                // dailyRecord1.setWeight(50);
                // dailyRecord1.setBodyFat(15);
                // dailyRecord1.setDrinkingWater(1000);
                // dailyRecord1.setMembers(null);

                // DailyRecord dailyRecord2 = new DailyRecord();
                // dailyRecord2.setWeight(50);
                // dailyRecord2.setBodyFat(15);
                // dailyRecord2.setDrinkingWater(1000);
                // dailyRecord2.setMembers(null);

                // // 日記食物細節
                // Food_daily food_daily1 = new Food_daily();
                // food_daily1.setSide(1);
                // food_daily1.setFood(food1);
                // food_daily1.setDaily_record(dailyRecord1);
                // foodDailyRespository.save(food_daily1);

                // Food_daily food_daily2 = new Food_daily();
                // food_daily2.setSide(2);
                // food_daily2.setFood(food2);
                // food_daily2.setDaily_record(dailyRecord2);
                // foodDailyRespository.save(food_daily2);

                // // 日記運動細節
                // Sports_daily sports_daily1 = new Sports_daily();
                // sports_daily1.setTime(1);
                // sports_daily1.setSports(sports1);
                // sports_daily1.setDaily_record(dailyRecord1);
                // sportsDailyRespository.save(sports_daily1);

                // Sports_daily sports_daily2 = new Sports_daily();
                // sports_daily2.setTime(2);
                // sports_daily2.setSports(sports2);
                // sports_daily2.setDaily_record(dailyRecord2);
                // sportsDailyRespository.save(sports_daily2);

                // 插入影片
                byte[] video1 = Files
                                .readAllBytes(Paths.get(
                                                "src/main/resources/static/public/video/939eaf7939f3495ebab182315ff76849.jpg"));
                Video v1 = new Video();
                v1.setTitle("四足俯臥撐");
                v1.setType("胸肌");
                v1.setBody_parts("胸大肌");
                v1.setPicture(video1);
                v1.setViews(0);
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

                // 產品
                Product product = new Product();
                product.setTitle("雙速咖啡因雙層錠");
                product.setType("保健食品");
                product.setPrice(840);
                product.setStock(500);
                product.setText("專為耐力運動設計，每錠200mg咖啡因");
                product.setOnsale(1);
                product.setVolume(0);
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
                product2.setOnsale(1);
                product2.setVolume(0);
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
                product3.setOnsale(1);
                product3.setVolume(0);
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
                product4.setOnsale(1);
                product4.setVolume(0);
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
                product5.setOnsale(1);
                product5.setVolume(0);
                String saveFiledir5 = System.getProperty("user.dir") + "/src/main/resources/static/img/product/p5.jpg";
                File saveFilePath5 = new File(saveFiledir5);
                product5.setPicture(Files.readAllBytes(saveFilePath5.toPath()));
                pDao.save(product5);

                Product product6 = new Product();
                product6.setTitle("DHC濃縮薑黃");
                product6.setType("保健食品");
                product6.setPrice(356);
                product6.setStock(0);
                product6.setText("台灣DHC直供．品質保證");
                product6.setOnsale(1);
                product6.setVolume(0);
                String saveFiledir6 = System.getProperty("user.dir") + "/src/main/resources/static/img/product/p6.jpg";
                File saveFilePath6 = new File(saveFiledir6);
                product6.setPicture(Files.readAllBytes(saveFilePath6.toPath()));
                pDao.save(product6);

                Product product7 = new Product();
                product7.setTitle("ADISI瑜珈墊");
                product7.setType("運動用品");
                product7.setPrice(880);
                product7.setStock(50);
                product7.setText("適合瑜珈、皮拉提斯、體適能球操等運動");
                product7.setOnsale(1);
                product7.setVolume(0);
                String saveFiledir7 = System.getProperty("user.dir") +
                                "/src/main/resources/static/img/product/p7.jpg";
                File saveFilePath7 = new File(saveFiledir7);
                product7.setPicture(Files.readAllBytes(saveFilePath7.toPath()));
                pDao.save(product7);

                Product product8 = new Product();
                product8.setTitle("易利氣磁力項圈");
                product8.setType("運動用品");
                product8.setPrice(611);
                product8.setStock(0);
                product8.setText("日本銷售逾1千萬條");
                product8.setOnsale(1);
                product8.setVolume(0);
                String saveFiledir8 = System.getProperty("user.dir") +
                                "/src/main/resources/static/img/product/p8.jpg";
                File saveFilePath8 = new File(saveFiledir8);
                product8.setPicture(Files.readAllBytes(saveFilePath8.toPath()));
                pDao.save(product8);

                Product product9 = new Product();
                product9.setTitle("Adidas Strength 吊鉤式舉重助力帶");
                product9.setType("運動用品");
                product9.setPrice(790);
                product9.setStock(2);
                product9.setText("適合瑜珈、皮拉提斯、體適能球操等運動");
                product9.setOnsale(1);
                product9.setVolume(0);
                String saveFiledir9 = System.getProperty("user.dir") +
                                "/src/main/resources/static/img/product/p9.jpg";
                File saveFilePath9 = new File(saveFiledir9);
                product9.setPicture(Files.readAllBytes(saveFilePath9.toPath()));
                pDao.save(product9);

                Product product10 = new Product();
                product10.setTitle("智能計數呼拉圈");
                product10.setType("運動用品");
                product10.setPrice(590);
                product10.setStock(1);
                product10.setText("高效燃燒脂肪,可邊旋轉邊按摩,深層刺激燃燒脂肪");
                product10.setOnsale(1);
                product10.setVolume(0);
                String saveFiledir10 = System.getProperty("user.dir") +
                                "/src/main/resources/static/img/product/p10.jpg";
                File saveFilePath10 = new File(saveFiledir10);
                product10.setPicture(Files.readAllBytes(saveFilePath10.toPath()));
                pDao.save(product10);

                Product product11 = new Product();
                product11.setTitle("刺蝟造型震動按摩枕");
                product11.setType("運動用品");
                product11.setPrice(930);
                product11.setStock(10);
                product11.setText("蓬鬆好笑的刺蝟造型,每天的好夥伴送禮也很棒");
                product11.setOnsale(1);
                product11.setVolume(0);
                String saveFiledir11 = System.getProperty("user.dir") +
                                "/src/main/resources/static/img/product/p11.jpg";
                File saveFilePath11 = new File(saveFiledir11);
                product11.setPicture(Files.readAllBytes(saveFilePath11.toPath()));
                pDao.save(product11);

                Product product12 = new Product();
                product12.setTitle("兩用小腿背部按摩拉筋板");
                product12.setType("運動用品");
                product12.setPrice(490);
                product12.setStock(3);
                product12.setText("網路最夯的按摩神器");
                product12.setOnsale(1);
                product12.setVolume(0);
                String saveFiledir12 = System.getProperty("user.dir") +
                                "/src/main/resources/static/img/product/p12.jpg";
                File saveFilePath12 = new File(saveFiledir12);
                product12.setPicture(Files.readAllBytes(saveFilePath12.toPath()));
                pDao.save(product12);

                Product product13 = new Product();
                product13.setTitle("【桂格】養氣人蔘滋補液60ml");
                product13.setType("運動用品");
                product13.setPrice(399);
                product13.setStock(5);
                product13.setText("有助於降低血清 GOT、GPT 值");
                product13.setOnsale(1);
                product13.setVolume(0);
                String saveFiledir13 = System.getProperty("user.dir") +
                                "/src/main/resources/static/img/product/p13.jpg";
                File saveFilePath13 = new File(saveFiledir13);
                product13.setPicture(Files.readAllBytes(saveFilePath13.toPath()));
                pDao.save(product13);

                Product product14 = new Product();
                product14.setTitle("【葡萄王】樟芝王(100粒/瓶)");
                product14.setType("運動食品");
                product14.setPrice(1197);
                product14.setStock(0);
                product14.setText("◆樟芝多醣9%補精力有活力 ◆技術創新 榮獲國家七項專利");
                product14.setOnsale(1);
                product14.setVolume(0);
                String saveFiledir14 = System.getProperty("user.dir") +
                                "/src/main/resources/static/img/product/p14.jpg";
                File saveFilePath14 = new File(saveFiledir14);
                product14.setPicture(Files.readAllBytes(saveFilePath14.toPath()));
                pDao.save(product14);

                Product product15 = new Product();
                product15.setTitle("【達摩本草】法國西印度櫻桃植萃天然維他命C");
                product15.setType("運動食品");
                product15.setPrice(290);
                product15.setStock(2);
                product15.setText("無化學合成，西印度櫻桃植萃");
                product15.setOnsale(1);
                product15.setVolume(0);
                String saveFiledir15 = System.getProperty("user.dir") +
                                "/src/main/resources/static/img/product/p15.jpg";
                File saveFilePath15 = new File(saveFiledir15);
                product15.setPicture(Files.readAllBytes(saveFilePath15.toPath()));
                pDao.save(product15);

        }

}