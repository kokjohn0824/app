package com.finalpretty.app.email;

import java.util.HashMap;
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

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    private final String LINK = "http://localhost:8082/public/api/registration/confirm?token=";

    @Autowired
    private SpringTemplateEngine thymeleafTemplateEngine;

    @Override
    @Async
    public void send(EmailBean emailBean) {
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
        String verificationLink = LINK + token;
        // 將連結塞入Map中
        templateModel.put("verificationLink", verificationLink);
        // 設定變數至引擎中
        thymeleafContext.setVariables(templateModel);
        // 將指定模板轉換成html字串
        String htmlBody = thymeleafTemplateEngine.process("/email/verificationEmail.html", thymeleafContext);
        emailBean.setContent(htmlBody);
        // 呼叫傳送信件method
        send(emailBean);
    }
}
