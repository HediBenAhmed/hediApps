package com.hediapps.rest.authentication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hediapps.backend.model.Email;
import com.hediapps.backend.model.User;
import com.hediapps.rest.transfer.TokenTransfer;
import com.hediapps.rest.transfer.UserTransfer;
import com.hediapps.rest.utils.TokenUtils;
import com.hediapps.service.UserService;

@Component
@RequestMapping("/rest/loginService")
public class LoginService {
	@Autowired
	private UserService userService;

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
	 */
	@RequestMapping(value = "authenticate", method = RequestMethod.POST)
	@ResponseBody
	public TokenTransfer authenticate(@RequestParam("username") String username,
			@RequestParam("password") String password) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		Authentication authentication = this.authManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		/*
		 * Reload user as password of authentication principal will be null
		 * after authorization and password is needed for token generation
		 */
		UserDetails userDetails = this.userService.loadUserByUsername(username);
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

		User userDetails = (User) principal;

		List<Email> messages = userService.getEmails(userDetails.getId(), false);

		return new UserTransfer(userDetails.getId(), userDetails.getFirstName(), userDetails.getLastName(),
				userDetails.getEmail(), this.createRoleMap(userDetails), messages.size(), 0);
	}

	private Map<String, Boolean> createRoleMap(UserDetails userDetails) {
		Map<String, Boolean> roles = new HashMap<String, Boolean>();
		for (GrantedAuthority authority : userDetails.getAuthorities()) {
			roles.put(authority.getAuthority(), Boolean.TRUE);
		}

		return roles;
	}
}
