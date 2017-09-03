package com.hediapps.authentification.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hediapps.authentification.service.AuthentificationService;

@Controller
@RequestMapping("/authentification")
public class AuthentificationController {
	@Autowired
	private AuthentificationService authentificationService;

	public AuthentificationService getAuthentificationService() {
		return authentificationService;
	}

	public void setAuthentificationService(AuthentificationService authentificationService) {
		this.authentificationService = authentificationService;
	}
}
