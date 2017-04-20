package com.hediapps.rest.resources;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hediapps.backend.model.Email;
import com.hediapps.backend.model.Message;
import com.hediapps.service.MessageService;

@Component
@RequestMapping("/rest/messages")
public class MessageResource {
	private static final Logger logger = LoggerFactory.getLogger(MessageResource.class);

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "dummy", method = RequestMethod.GET)
	@ResponseBody
	public Message getDummyEmail() {
		logger.info("Start getDummyMessage");
		Email data = new Email();
		data.setId(0);
		data.setCreationDate(new Date());
		data.setFromUser(1l);
		data.setSubject("dummy");
		data.setText("text");
		data.setToUser(2l);

		messageService.create(data);

		return data;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public Message getMessage(@PathVariable long id) {
		logger.info("Start getMessage. ID=" + id);

		return messageService.readById(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Message> getAllMessages() {
		return messageService.readAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Message createMessage(Message data) {
		logger.info("Start createMessage.");

		messageService.create(data);
		return data;
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Message updateMessage(Message data) {
		logger.info("Start updateNode.");
		messageService.update(data);
		return data;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Message deleteMessage(@PathVariable long id) {

		logger.info("Start deleteMessage.");
		Message data = messageService.readById(id);
		messageService.deleteById(id);
		return data;
	}

}
