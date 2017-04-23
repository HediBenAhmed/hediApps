package com.hediapps.rest.resources;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hediapps.backend.model.Email;
import com.hediapps.backend.model.Role;
import com.hediapps.backend.model.User;
import com.hediapps.rest.transfer.EmailTransfer;
import com.hediapps.service.UserService;

@Component
@RequestMapping("/rest/users")
public class UserResource {
	private static final Logger logger = LoggerFactory.getLogger(UserResource.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "dummy", method = RequestMethod.GET)
	@ResponseBody
	public User getDummyUser() {

		Set<Role> roles = new HashSet<Role>();
		roles.add(Role.ADMIN);

		User data = new User(1l, "user", "user", "pwd", "email@com", true, roles);

		return data;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public User getUser(@PathVariable long id) {
		logger.info("Start getUser. ID=" + id);

		return userService.readById(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUsers() {
		logger.info("Start getAllUsers.");
		return userService.readAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public User createUser(User data) {
		logger.info("Start createUser.");

		userService.create(data);
		return data;
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public User updateUser(User data) {
		logger.info("Start updateNode.");
		userService.update(data);
		return data;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public User deleteUser(@PathVariable long id) {
		logger.info("Start deleteUser.");
		User data = userService.readById(id);
		userService.deleteById(id);
		return data;
	}

	@RequestMapping(value = "{id}/messages", method = RequestMethod.GET)
	@ResponseBody
	public List<EmailTransfer> getEmails(@PathVariable long id) {

		List<Email> messages = userService.getEmails(id, false);

		List<EmailTransfer> messagesTransfer = null;
		if (messages != null) {
			messagesTransfer = new ArrayList<EmailTransfer>();
			for (Email message : messages) {

				EmailTransfer messageTransfer = new EmailTransfer(message);

				messagesTransfer.add(messageTransfer);
			}
		}

		return messagesTransfer;
	}
}
