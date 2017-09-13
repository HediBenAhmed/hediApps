package com.hediapps.authentification.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthentificationController {
	
	@PreAuthorize("#oauth2.hasScope('read')")
	@RequestMapping(path = "/userinfos", method = RequestMethod.GET)
	public Authentication userinfo(Authentication authentication) {
		return authentication;
	}
}
