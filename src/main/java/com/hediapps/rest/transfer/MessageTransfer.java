package com.hediapps.rest.transfer;

public class MessageTransfer {

	private UserTransfer fromUser;
	private UserTransfer toUser;
	private long creationDate;
	private String subject;
	private String text;
	private boolean read;

	public MessageTransfer() {

	}

	public MessageTransfer(UserTransfer fromUser, UserTransfer toUser, long creationDate, String subject, String text,
			boolean read) {
		super();
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.creationDate = creationDate;
		
		this.subject = subject;
		this.text = text;
		this.read = read;
	}

	public UserTransfer getFromUser() {
		return fromUser;
	}

	public void setFromUser(UserTransfer fromUser) {
		this.fromUser = fromUser;
	}

	public UserTransfer getToUser() {
		return toUser;
	}

	public void setToUser(UserTransfer toUser) {
		this.toUser = toUser;
	}

	public long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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