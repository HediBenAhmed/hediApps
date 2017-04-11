package com.hediapps.backend.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "common")
public class Email extends Message {

	private long fromUser;
	private long toUser;
	private String subject;

	public Email() {
		super();
	}

	public Email(long id, long fromUser, long toUser, Date creationDate,
			String subject, String text) {
		super(id, creationDate, text);
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.subject = subject;
	}

	public long getFromUser() {
		return fromUser;
	}

	public void setFromUser(long fromUser) {
		this.fromUser = fromUser;
	}

	public long getToUser() {
		return toUser;
	}

	public void setToUser(long toUser) {
		this.toUser = toUser;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
