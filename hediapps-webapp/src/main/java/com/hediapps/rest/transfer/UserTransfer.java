package com.hediapps.rest.transfer;

import java.util.Map;

public class UserTransfer {

	private long id;

	private String firstName;
	private String lastName;
	
	private String email;
	
	private int newMessages;

	private int currentTasks;

	private final Map<String, Boolean> roles;

	public UserTransfer(long id, String firstName,String lastName,String email, Map<String, Boolean> roles, int newMessages, int currentTasks) {
		this.id = id;
		this.firstName = firstName;
		this.lastName =lastName;
		this.email = email;
		this.roles = roles;
		this.newMessages = newMessages;
		this.currentTasks = currentTasks;
	}

	public Map<String, Boolean> getRoles() {
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
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