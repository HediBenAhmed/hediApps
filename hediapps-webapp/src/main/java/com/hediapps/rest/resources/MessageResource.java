package com.hediapps.rest.resources;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hediapps.backend.model.Message;
import com.hediapps.service.MessageService;

@Component
@Path("/messages")
public class MessageResource {
	private static final Logger logger = LoggerFactory.getLogger(MessageResource.class);

	@Autowired
	private MessageService messageService;

	@Path("dummy")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Message getDummyMessage() {
		logger.info("Start getDummyMessage");
		Message data = new Message();
		data.setId(0);
		data.setCreationDate(new Date());
		data.setFromUser(1l);
		data.setSubject("dummy");
		data.setText("text");
		data.setToUser(2l);
		
		messageService.create(data);
		
		return data;
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("id") long dataId) {
		logger.info("Start getMessage. ID=" + dataId);

		return messageService.readById(dataId);
	}

	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getAllMessages() {
		return messageService.readAll();
	}

	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message createMessage(Message data) {
		logger.info("Start createMessage.");

		messageService.create(data);
		return data;
	}

	@Path("/")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(Message data) {
		logger.info("Start updateNode.");
		messageService.update(data);
		return data;
	}

	@Path("/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Message deleteMessage(@PathParam("id") long dataId) {

		logger.info("Start deleteMessage.");
		Message data = messageService.readById(dataId);
		messageService.deleteById(dataId);
		return data;
	}

}
