package com.hediapps.controller;

import javax.servlet.http.Cookie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class IndexController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {

		
			return "index.html";
		
	}
	//
	// @RequestMapping(value = "/task", method = RequestMethod.GET)
	// public String task(@CookieValue(value = "user", required = false) Cookie
	// userCookie) {
	// if (userCookie != null &&
	// LoginController.checkTocken(userCookie.getValue()))
	// return "task";
	// else
	// return "login";
	// }
	//
	// @RequestMapping(value = "/basic_form", method = RequestMethod.GET)
	// public String basic_form(@CookieValue(value = "user", required = false)
	// Cookie userCookie) {
	// if (userCookie != null &&
	// LoginController.checkTocken(userCookie.getValue()))
	// return "basic_form";
	// else
	// return "login";
	// }
	//
	// @RequestMapping(value = "/general", method = RequestMethod.GET)
	// public String general(@CookieValue(value = "user", required = false)
	// Cookie userCookie) {
	// if (userCookie != null &&
	// LoginController.checkTocken(userCookie.getValue()))
	// return "general";
	// else
	// return "login";
	// }
	//
	// @RequestMapping(value = "/simple", method = RequestMethod.GET)
	// public String simple(@CookieValue(value = "user", required = false)
	// Cookie userCookie) {
	// if (userCookie != null &&
	// LoginController.checkTocken(userCookie.getValue()))
	// return "simple";
	// else
	// return "login";
	// }

	// @RequestMapping(value = "/login", method = RequestMethod.GET)
	// public String login() {
	// return "login.html";
	// }

	// @RequestMapping(value = "/graph", method = RequestMethod.GET)
	// public String t() {
	// return "graph";
	// }
}
