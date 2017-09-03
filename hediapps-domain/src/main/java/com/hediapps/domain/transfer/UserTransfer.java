package com.hediapps.domain.transfer;

import java.util.Set;

import com.hediapps.domain.model.Role;
import com.hediapps.domain.model.User;

public class UserTransfer {

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private int newMessages;
	private int currentTasks;
	private Set<Role> roles;

	public UserTransfer() {
	}

	public UserTransfer(String id, String firstName, String lastName, String email, Set<Role> roles, int newMessages,
			int currentTasks) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roles = roles;
		this.newMessages = newMessages;
		this.currentTasks = currentTasks;
	}

	public UserTransfer(User user, int newMessages, int currentTasks) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.roles = (Set<Role>) user.getAuthorities();
		this.newMessages = newMessages;
		this.currentTasks = currentTasks;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public int getCurrentTasks() {
		return currentTasks;
	}

	public void setCurrentTasks(int currentTasks) {
		this.currentTasks = currentTasks;
	}

	public int getNewMessages() {
		return newMessages;
	}

	public void setNewMessages(int newMessages) {
		this.newMessages = newMessages;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}