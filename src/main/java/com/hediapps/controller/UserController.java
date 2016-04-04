package com.hediapps.controller;

import java.util.List;

import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hediapps.model.User;
import com.hediapps.service.UserService;

@Controller
@RequestMapping("/UserService")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/dummy", method = RequestMethod.GET)
	public @ResponseBody User getDummyUser(@CookieValue(value = "user") Cookie userCookie) {
		logger.info("Start getDummyUser");
		User data = new User();
		data.setId(0);
		data.setLogin("user@com");
		data.setPassword("pwd");
		data.setLogin("login");
		return data;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody User getUser(@CookieValue(value = "user") Cookie userCookie, @PathVariable("id") long userId) {
		logger.info("Start getUser. ID=" + userId);

		return userService.readById(userId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public @ResponseBody List<User> getAllUsers(@CookieValue(value = "user") Cookie userCookie) {
		logger.info("Start getAllUsers.");
		return userService.readAll();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody User createUser(@CookieValue(value = "user") Cookie userCookie, @RequestBody User data) {
		logger.info("Start createUser.");

		userService.create(data);
		return data;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody User updateUser(@CookieValue(value = "user") Cookie userCookie, @RequestBody User data) {
		logger.info("Start updateNode.");
		userService.update(data);
		return data;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody User deleteUser(@CookieValue(value = "user") Cookie userCookie,
			@PathVariable("id") long dataId) {
		logger.info("Start deleteUser.");
		User data = userService.readById(dataId);
		userService.deleteById(dataId);
		return data;
	}

}
