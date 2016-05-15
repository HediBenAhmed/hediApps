package com.hediapps.rest.transfer;

import java.util.Map;

public class UserTransfer {

	private String name;

	private int newMessages;

	private int currentTasks;

	private final Map<String, Boolean> roles;

	public UserTransfer(String userName, Map<String, Boolean> roles, int newMessages, int currentTasks) {
		this.name = userName;
		this.roles = roles;
		this.newMessages = newMessages;
		this.currentTasks = currentTasks;
	}

	public String getName() {
		return this.name;
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

}