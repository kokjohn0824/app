package com.finalpretty.app.email;

public interface EmailSender {
    void send(EmailBean emailBean);

    void verificationEmailsend(String to, String token);

    String resetPwdEmailsend(String emailinput, String resetPwdToken);
}
