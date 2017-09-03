package com.hediapps.authentification.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hediapps.authentification.service.AuthentificationService;
import com.hediapps.authentification.utils.TokenUtils;
import com.hediapps.domain.model.User;
import com.hediapps.domain.transfer.TokenTransfer;
import com.hediapps.domain.transfer.UserTransfer;

@Controller
@RequestMapping("/authentification")
public class AuthentificationController {
	@Autowired
	private AuthentificationService authentificationService;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;

	/**
	 * Authenticates a user and creates an authentication token.
	 * 
	 * @param username
	 *            The name of the user.
	 * @param password
	 *            The password of the user.
	 * @return A transfer containing the authentication token.
	 * @throws Exception
	 */
	@RequestMapping(value = "authenticate", method = RequestMethod.POST)
	@ResponseBody
	public TokenTransfer authenticate(@RequestParam("username") String username,
			@RequestParam("password") String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		Authentication authentication = this.authManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		/*
		 * Reload user as password of authentication principal will be null
		 * after authorization and password is needed for token generation
		 */
		UserDetails userDetails = this.authentificationService.loadUserByUsername(username);
		return new TokenTransfer(TokenUtils.createToken(userDetails));
	}

	@RequestMapping(value = "current", method = RequestMethod.GET)
	@ResponseBody
	public UserTransfer getUser() throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
			throw new Exception("401");
		}

		return new UserTransfer((User) principal, 0, 0);
	}

	public AuthentificationService getAuthentificationService() {
		return authentificationService;
	}

	public void setAuthentificationService(AuthentificationService authentificationService) {
		this.authentificationService = authentificationService;
	}
}
