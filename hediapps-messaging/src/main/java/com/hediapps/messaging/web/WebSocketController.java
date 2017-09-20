package com.hediapps.messaging.web;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hediapps.messaging.domain.transfer.MessageTransfer;

@RestController
public class WebSocketController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "test";
	}

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