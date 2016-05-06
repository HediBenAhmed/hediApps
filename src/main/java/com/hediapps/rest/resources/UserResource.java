package com.hediapps.rest.resources;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hediapps.model.Role;
import com.hediapps.model.User;
import com.hediapps.service.UserService;

@Component
@Path("/user")
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

		User data = new User(1l, "user@com", "pwd", "pwdSal", "email@com", true, roles);

		return data;
	}

	/*
	 * @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	 * public @ResponseBody User getUser(@CookieValue(value = "user") Cookie
	 * userCookie, @PathVariable("id") long userId) { logger.info(
	 * "Start getUser. ID=" + userId);
	 * 
	 * return userService.readById(userId); }
	 * 
	 * @RequestMapping(value = "/", method = RequestMethod.GET)
	 * public @ResponseBody List<User> getAllUsers(@CookieValue(value = "user")
	 * Cookie userCookie) { logger.info("Start getAllUsers."); return
	 * userService.readAll(); }
	 * 
	 * @RequestMapping(value = "/", method = RequestMethod.POST)
	 * public @ResponseBody User createUser(@CookieValue(value = "user") Cookie
	 * userCookie, @RequestBody User data) { logger.info("Start createUser.");
	 * 
	 * userService.create(data); return data; }
	 * 
	 * @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	 * public @ResponseBody User updateUser(@CookieValue(value = "user") Cookie
	 * userCookie,
	 * 
	 * @PathVariable("id") long userId, @RequestBody User data) { logger.info(
	 * "Start updateNode."); userService.update(data); return data; }
	 * 
	 * @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	 * public @ResponseBody User deleteUser(@CookieValue(value = "user") Cookie
	 * userCookie,
	 * 
	 * @PathVariable("id") long dataId) { logger.info("Start deleteUser."); User
	 * data = userService.readById(dataId); userService.deleteById(dataId);
	 * return data; }
	 */

}
