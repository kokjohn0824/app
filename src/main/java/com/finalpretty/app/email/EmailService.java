package com.finalpretty.app.email;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.finalpretty.app.Response.OrderDetailDto;
import com.finalpretty.app.Response.OrderDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    private final String verificationLink = "http://localhost:8082/public/api/registration/public/confirm?token=";
    private final String resetPwdLink = "http://localhost:8082/public/user/confirmresetpwd?token=";

    @Autowired
    private SpringTemplateEngine thymeleafTemplateEngine;

    @Override
    @Async
    public void send(EmailBean emailBean) throws IllegalStateException {
        MimeMessage message;

        try {

            MimeMessageHelper helper = new MimeMessageHelper(message = mailSender.createMimeMessage(), "utf-8");
            helper.setText(emailBean.getContent(), true);
            helper.setTo(emailBean.getTo());
            helper.setSubject(emailBean.getSubject());
            helper.setFrom("Digimon@everyday.com");
            mailSender.send(message);

        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }

    }

    @Override
    public void verificationEmailsend(String to, String token) {
        // 生成 Email Bean
        EmailBean emailBean = new EmailBean();
        // 設定 收信者
        emailBean.setTo(to);
        // 設定信件標題
        emailBean.setSubject("驗證您的帳號電子郵件");

        // 載入 thymleaf 引擎
        Context thymeleafContext = new Context();
        // 生成Map 來塞入變數
        Map<String, Object> templateModel = new HashMap<>();
        // 將token接上網址
        String LINK = verificationLink + token;
        // 將連結塞入Map中
        templateModel.put("verificationLink", LINK);
        // 設定變數至引擎中
        thymeleafContext.setVariables(templateModel);
        // 將指定模板轉換成html字串
        String htmlBody = thymeleafTemplateEngine.process("/email/verificationEmail.html", thymeleafContext);
        emailBean.setContent(htmlBody);
        // 呼叫傳送信件method
        try {
            send(emailBean);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String resetPwdEmailsend(String to, String resetPwdToken) {
        // 生成 Email Bean
        EmailBean emailBean = new EmailBean();
        // 設定 收信者
        emailBean.setTo(to);
        // 設定信件標題
        emailBean.setSubject("重新設定您的密碼");

        // 載入 thymleaf 引擎
        Context thymeleafContext = new Context();
        // 生成Map 來塞入變數
        Map<String, Object> templateModel = new HashMap<>();
        // 將token接上網址
        String LINK = resetPwdLink + resetPwdToken;
        // 將連結塞入Map中
        templateModel.put("resetPwdLink", LINK);
        // 設定變數至引擎中
        thymeleafContext.setVariables(templateModel);
        // 將指定模板轉換成html字串
        String htmlBody = thymeleafTemplateEngine.process("/email/resetPwdLinkEmail.html", thymeleafContext);
        emailBean.setContent(htmlBody);
        // 呼叫傳送信件method
        try {
            send(emailBean);
            return "success";
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }

    @Override
    public String orderEmailSend(String emailinput, OrderDto order, List<OrderDetailDto> detail) {
        EmailBean emailBean = new EmailBean();
        emailBean.setTo(emailinput);
        // 設定信件標題
        emailBean.setSubject("您此筆訂單尚未付款");
        Context thymeleafContext = new Context();
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("order", order);
        templateModel.put("detail", detail);
        System.out.println("++++++++++++++eqweqwe");
        System.out.println(order.getOrder_id());
        String ecpay = "http://localhost:8082/public/ecpay/test/" + order.getOrder_id();
        templateModel.put("ecpay", ecpay);
        thymeleafContext.setVariables(templateModel);
        String htmlBody = thymeleafTemplateEngine.process("/email/resetOrderPayment.html", thymeleafContext);
        emailBean.setContent(htmlBody);

        try {
            send(emailBean);
            return "success";
        } catch (IllegalStateException e) {
            return e.getMessage();
        }

    }

    @Override
    public String userEmailSend(String emailinput) {
        EmailBean emailBean = new EmailBean();
        emailBean.setTo(emailinput);
        // 設定信件標題
        emailBean.setSubject("您的帳號因部分因素已被停權請洽客服");
        Context thymeleafContext = new Context();
        Map<String, Object> templateModel = new HashMap<>();
        // templateModel.put("nickname", nickname);
        thymeleafContext.setVariables(templateModel);
        String htmlBody = thymeleafTemplateEngine.process("/email/resetUsers.html", thymeleafContext);
        emailBean.setContent(htmlBody);

        try {
            send(emailBean);
            return "success";
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }

    @Override
    public String userOnEmailSend(String emailinput) {
        EmailBean emailBean = new EmailBean();
        emailBean.setTo(emailinput);
        // 設定信件標題
        emailBean.setSubject("您的帳號已復權");
        Context thymeleafContext = new Context();
        Map<String, Object> templateModel = new HashMap<>();
        // templateModel.put("nickname", nickname);
        thymeleafContext.setVariables(templateModel);
        String htmlBody = thymeleafTemplateEngine.process("/email/resetOnUsers.html", thymeleafContext);
        emailBean.setContent(htmlBody);

        try {
            send(emailBean);
            return "success";
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }
}
