package com.hediapps.messaging.controller;

import java.util.Date;

import com.hediapps.messaging.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "test";
    }

    @MessageMapping("/sendPublicMessage")
    public void sendPublicMessage(MessageDto message) {

        message.setCreationDate(new Date().getTime());
        message.setRead(false);

        log.debug("{} => {} : {}", message.getFromUser().getEmail(), message.getDestination(), message.getText());

        simpMessagingTemplate.convertAndSend(message.getDestination(), message);
    }

    @MessageMapping("/sendPrivateMessage")
    public void sendPrivateMessage(MessageDto message) {
        simpMessagingTemplate.convertAndSendToUser("login@com", "/messages", message);
    }

    public void brodcastSystemEvents() {
        log.info("ping");
        simpMessagingTemplate.convertAndSend("/topic/messages",
                new MessageDto(null, null, new Date().getTime(), "ping", false));
    }
}
