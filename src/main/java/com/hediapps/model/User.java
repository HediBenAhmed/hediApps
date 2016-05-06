package com.hediapps.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Document(collection = "common")
public class User implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String username;
	private String password;
	private String passwordSalt;
	private String email;

	private Set<Role> authorities = new HashSet<Role>();

	private boolean credentialsNonExpired;
	private boolean enabled;
	private boolean accountNonLocked;
	private boolean accountNonExpired;

	public User() {
		super();
	}

	public User(long id, String username, String password, String passwordSalt, String email, boolean enabled,
			Set<Role> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.passwordSalt = passwordSalt;
		this.email = email;
		this.credentialsNonExpired = true;
		this.enabled = true;
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.enabled = enabled;
		this.authorities = authorities;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public String getUsername() {
		return this.username;
	}

	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", passwordSalt=" + passwordSalt
				+ ", email=" + email + ", credentialsNonExpired=" + credentialsNonExpired + ", enabled=" + enabled
				+ ", authorities=" + authorities + "]";
	}
}
