package com.finalpretty.app.email;

public interface EmailSender {
    void send(EmailBean emailBean);

    void verificationEmailsend(String to, String token);
}
