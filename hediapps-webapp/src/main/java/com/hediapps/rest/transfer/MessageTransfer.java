package com.hediapps.rest.transfer;

public class MessageTransfer {
	
	private UserTransfer fromUser;
	private String destination;
	private long creationDate;
	private String text;
	private boolean read;

	public MessageTransfer() {
	}

	public MessageTransfer(UserTransfer fromUser, String destination, long creationDate, String text, boolean read) {
		super();
		this.fromUser = fromUser;
		this.destination = destination;
		this.creationDate = creationDate;
		this.text = text;
		this.read = read;
	}

	public UserTransfer getFromUser() {
		return fromUser;
	}

	public void setFromUser(UserTransfer fromUser) {
		this.fromUser = fromUser;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}
}
