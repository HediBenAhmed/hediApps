package com.hediapps.rest;

public class ChatMessage {
	private String name;

	public ChatMessage() {
	}

	public ChatMessage(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}