package com.finalpretty.app.email;

import java.util.List;

import com.finalpretty.app.Response.OrderDetailDto;
import com.finalpretty.app.Response.OrderDto;

public interface EmailSender {
    void send(EmailBean emailBean);

    void verificationEmailsend(String to, String token);

    String resetPwdEmailsend(String emailinput, String resetPwdToken);

    String orderEmailSend(String emailinput, OrderDto order, List<OrderDetailDto> detail);

    String userEmailSend(String emailinput);

    String userOnEmailSend(String emailinput);
}
