package com.hediapps.controller;

import java.security.Key;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.hediapps.model.User;
import com.hediapps.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Controller
@RequestMapping("/loginService")
public class LoginController {
	@Autowired
	private UserService userService;

	public final static Key KEY = MacProvider.generateKey();

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public @ResponseBody User login(@RequestParam("userName") String userName, @RequestParam("pwd") String pwd,
			HttpServletResponse response) {

		User user = userService.findByLoinAndPassword(userName, pwd);

		if (user != null) {
			Gson gson = new Gson();
			String userJson = gson.toJson(user);
			String token = Jwts.builder().setSubject(userJson).signWith(SignatureAlgorithm.HS512, LoginController.KEY)
					.compact();

			Cookie newCookie = new Cookie("user", token);
			newCookie.setPath("/");
			response.addCookie(newCookie);

			return user;
		} else {
			response.setStatus(404);
			return null;
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public @ResponseBody void logout(@CookieValue(value = "user") Cookie userCookie, HttpServletResponse response) {

		Cookie newCookie = new Cookie("user", null);
		newCookie.setPath("/");
		newCookie.setMaxAge(0);

		response.addCookie(newCookie);
	}

	@RequestMapping(value = "/checkToken", method = RequestMethod.GET)
	public @ResponseBody String isValidTocken(@CookieValue(value = "user", required = false) Cookie userCookie) {
		if (userCookie == null)
			return "{\"valid\" : false}";
		if (LoginController.checkToken(userCookie.getValue())) {
			return "{\"valid\" : true}";
		} else
			return "{\"valid\" : false}";
	}

	public static boolean checkToken(String token) {
		try {

			Claims claims = Jwts.parser().setSigningKey(LoginController.KEY).parseClaimsJws(token).getBody();

			String userJson = claims.getSubject();

			Gson gson = new Gson();

			System.out.println(gson.fromJson(userJson, User.class));

			return true;
		} catch (SignatureException e) {
			e.printStackTrace();
			return false;
		}
	}
}
