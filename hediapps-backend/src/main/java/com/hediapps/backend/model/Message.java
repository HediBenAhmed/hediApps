package com.hediapps.backend.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "common")
public class Message {

	@Id
	private long id;

	private long fromUser;

	private long toUser;
	private long creationDate;
	private String subject;
	private String text;

	private boolean read;

	public Message() {
		super();
	}

	public Message(long id, long fromUser, long toUser, Date creationDate, String subject, String text) {
		super();
		this.id = id;
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.creationDate = creationDate == null ? 0l : creationDate.getTime();
		this.subject = subject;
		this.text = text;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate == null ? 0l : creationDate.getTime();
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

	@Override
	public String toString() {
		return "Message [id=" + id + ", fromUser=" + fromUser + ", toUser=" + toUser + ", creationDate=" + creationDate
				+ ", subject=" + subject + ", text=" + text + "]";
	}

}
