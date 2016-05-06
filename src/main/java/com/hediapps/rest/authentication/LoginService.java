package com.hediapps.rest.authentication;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.hediapps.rest.transfer.TokenTransfer;
import com.hediapps.rest.transfer.UserTransfer;
import com.hediapps.rest.utils.TokenUtils;
import com.hediapps.service.UserService;

import io.jsonwebtoken.impl.crypto.MacProvider;

@Component
@Path("/loginService")
public class LoginService {
	@Autowired
	private UserService userService;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;

	public final static Key KEY = MacProvider.generateKey();

	/*
	 * @RequestMapping(value = "/logout", method = RequestMethod.GET)
	 * public @ResponseBody void logout(@CookieValue(value = "user") Cookie
	 * userCookie, HttpServletResponse response) {
	 * 
	 * Cookie newCookie = new Cookie("user", null); newCookie.setPath("/");
	 * newCookie.setMaxAge(0);
	 * 
	 * response.addCookie(newCookie); }
	 * 
	 * @RequestMapping(value = "/checkToken", method = RequestMethod.GET)
	 * public @ResponseBody String isValidTocken(@CookieValue(value = "user",
	 * required = false) Cookie userCookie) { if (userCookie == null) return
	 * "{\"valid\" : false}"; if
	 * (LoginController.checkToken(userCookie.getValue())) { return
	 * "{\"valid\" : true}"; } else return "{\"valid\" : false}"; }
	 * 
	 * public static boolean checkToken(String token) { try {
	 * 
	 * Claims claims =
	 * Jwts.parser().setSigningKey(LoginController.KEY).parseClaimsJws(token).
	 * getBody();
	 * 
	 * String userJson = claims.getSubject();
	 * 
	 * Gson gson = new Gson();
	 * 
	 * System.out.println(gson.fromJson(userJson, User.class));
	 * 
	 * return true; } catch (SignatureException e) { e.printStackTrace(); return
	 * false; } }
	 */

	/**
	 * Authenticates a user and creates an authentication token.
	 * 
	 * @param username
	 *            The name of the user.
	 * @param password
	 *            The password of the user.
	 * @return A transfer containing the authentication token.
	 */
	@Path("authenticate")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public TokenTransfer authenticate(@FormParam("username") String username, @FormParam("password") String password) {

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

	@Path("current")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserTransfer getUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
			throw new WebApplicationException(401);
		}

		UserDetails userDetails = (UserDetails) principal;

		return new UserTransfer(userDetails.getUsername(), this.createRoleMap(userDetails));
	}

	private Map<String, Boolean> createRoleMap(UserDetails userDetails) {
		Map<String, Boolean> roles = new HashMap<String, Boolean>();
		for (GrantedAuthority authority : userDetails.getAuthorities()) {
			roles.put(authority.getAuthority(), Boolean.TRUE);
		}

		return roles;
	}
}
