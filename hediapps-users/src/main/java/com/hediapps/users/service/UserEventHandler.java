package com.hediapps.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.hediapps.authentification.domain.model.User;

@Component
@RepositoryEventHandler(User.class)
public class UserEventHandler {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@HandleBeforeCreate
	public void handleUserCreate(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
	}

	@HandleBeforeSave
	public void handleUserUpdate(User user) {

		// password change request
		user.setPassword(passwordEncoder.encode(user.getPassword()));
	}
}