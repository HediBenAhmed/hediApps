package com.hediapps.authentication.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hediapps.authentication.domain.model.User;
import com.hediapps.authentication.domain.transfer.UserTransfer;
import com.hediapps.authentication.service.UserRepository;

@RestController
public class AuthentificationController {

	@Autowired
	private UserRepository userRepository;

	@PreAuthorize("#oauth2.hasScope('read')")
	@RequestMapping(path = "/userinfos", method = RequestMethod.GET)
	public UserTransfer userinfo(Authentication authentication) {
		User user = userRepository.findOneByEmail(authentication.getName());

		UserTransfer userTransfer = new UserTransfer();
		userTransfer.setEmail(user.getEmail());
		userTransfer.setFirstName(user.getFirstName());
		userTransfer.setLastName(user.getLastName());

		return userTransfer;
	}
}
