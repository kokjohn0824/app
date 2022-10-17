package com.finalpretty.app.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {
    
    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    @MessageMapping("/test")
    public void sendTopicMessage(Messagebody messagebody) {
        simpMessageSendingOperations.convertAndSend(messagebody.getDestination(),messagebody );
    }

    @GetMapping("/websocketdemo")
    public String websocketdemo() {
        return "websocketdemo";
    }
}
