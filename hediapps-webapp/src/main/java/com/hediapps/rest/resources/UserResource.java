package com.hediapps.rest.resources;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.hediapps.backend.model.Role;
import com.hediapps.backend.model.User;
import com.hediapps.rest.transfer.MessageTransfer;
import com.hediapps.rest.transfer.UserTransfer;
import com.hediapps.service.UserService;

@Component
@Path("/users")
public class UserResource {
	private static final Logger logger = LoggerFactory.getLogger(UserResource.class);

	@Autowired
	private UserService userService;

	@Path("dummy")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getDummyUser() {

		Set<Role> roles = new HashSet<Role>();
		roles.add(Role.ADMIN);

		User data = new User(1l, "user", "user", "pwd", "email@com", true, roles);

		return data;
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("id") long userId) {
		logger.info("Start getUser. ID=" + userId);

		return userService.readById(userId);
	}

	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers() {
		logger.info("Start getAllUsers.");
		return userService.readAll();
	}

	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User createUser(User data) {
		logger.info("Start createUser.");

		userService.create(data);
		return data;
	}

	@Path("/")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User updateUser(User data) {
		logger.info("Start updateNode.");
		userService.update(data);
		return data;
	}

	@Path("/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public User deleteUser(@PathParam("id") long dataId) {
		logger.info("Start deleteUser.");
		User data = userService.readById(dataId);
		userService.deleteById(dataId);
		return data;
	}

	@Path("/{id}/messages")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MessageTransfer> getMessages(@PathParam("id") long userId) {

		List<Message> messages = userService.getMessages(userId);

		List<MessageTransfer> messagesTransfer = null;
		if (messages != null) {
			messagesTransfer = new ArrayList<MessageTransfer>();
			for (Message message : messages) {

				User from = userService.readById(message.getFromUser());

				MessageTransfer messageTransfer = new MessageTransfer(
						new UserTransfer(from.getId(), from.getFirstName(), from.getLastName(), from.getEmail(), null,
								0, 0),
						null, message.getCreationDate(), message.getSubject(), message.getText(), message.isRead());

				messagesTransfer.add(messageTransfer);
			}
		}

		return messagesTransfer;
	}
}
