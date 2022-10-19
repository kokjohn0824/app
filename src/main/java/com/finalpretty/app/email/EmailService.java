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
        EmailBean emailBean = new EmailBean();
        emailBean.setTo(to);
        emailBean.setSubject("驗證您的帳號電子郵件");

        // 帶入 thymleaf 引擎
        Context thymeleafContext = new Context();
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("token", token);
        thymeleafContext.setVariables(templateModel);
        String htmlBody = thymeleafTemplateEngine.process("/email/hihi.html", thymeleafContext);

        emailBean.setContent(htmlBody);
        send(emailBean);
    }
}
