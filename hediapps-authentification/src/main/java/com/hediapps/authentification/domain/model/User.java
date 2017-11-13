package com.hediapps.authentification.domain.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@Document(collection = "users")
public class User implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String firstName;

	private String lastName;

	private String password;
	private String email;

	private Set<Role> authorities = new HashSet<>();

	private boolean credentialsNonExpired;
	private boolean enabled;
	private boolean accountNonLocked;
	private boolean accountNonExpired;

	public String getUsername() {
		return this.email;
	}
}
