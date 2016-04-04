package com.hediapps.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "common")
public class User {
	@Id
	private long id;

	private String login;
	private String password;
	private String passwordSalt;
	private String email;

	private boolean verified;
	private boolean active;
	private boolean admin;

	public User() {
		super();
	}

	public User(long id, String login, String password, String passwordSalt, String email, boolean verified,
			boolean active, boolean admin) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.passwordSalt = passwordSalt;
		this.email = email;
		this.verified = verified;
		this.active = active;
		this.admin = admin;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", passwordSalt=" + passwordSalt
				+ ", email=" + email + ", verified=" + verified + ", active=" + active + ", admin=" + admin + "]";
	}

}
