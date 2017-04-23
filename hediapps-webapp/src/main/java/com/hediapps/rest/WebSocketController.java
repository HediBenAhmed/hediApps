package com.hediapps.rest;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

import com.hediapps.rest.transfer.MessageTransfer;

@Controller
@EnableScheduling
public class WebSocketController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/sendPublicMessage")
	public void sendPublicMessage(MessageTransfer message) throws Exception {

		message.setCreationDate(new Date().getTime());
		message.setRead(false);

		logger.debug("{} => {} : {}", message.getFromUser().getEmail(), message.getDestination(), message.getText());

		simpMessagingTemplate.convertAndSend(message.getDestination(), message);
	}

	@MessageMapping("/sendPrivateMessage")
	public void sendPrivateMessage(MessageTransfer message) throws Exception {
		simpMessagingTemplate.convertAndSendToUser("login@com", "/messages", message);
	}

	// @Scheduled(fixedDelay = 20000L)
	public void brodcastSystemEvents() {
		logger.info("ping");
		simpMessagingTemplate.convertAndSend("/topic/messages",
				new MessageTransfer(null, null, new Date().getTime(), "ping", false));
	}

	public SimpMessagingTemplate getSimpMessagingTemplate() {
		return simpMessagingTemplate;
	}

	public void setSimpMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate) {
		this.simpMessagingTemplate = simpMessagingTemplate;
	}
}