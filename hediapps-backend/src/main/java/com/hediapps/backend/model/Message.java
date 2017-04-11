package com.hediapps.backend.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "common")
public class Message {

	@Id
	private long id;

	private long creationDate;
	private String text;

	private boolean read;

	public Message() {
		super();
	}

	public Message(long id, Date creationDate, String text) {
		super();
		this.id = id;
		this.creationDate = creationDate == null ? 0l : creationDate.getTime();
		this.text = text;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
		return "Message [id=" + id + ", creationDate=" + creationDate
				+ ", text=" + text + ", read=" + read + "]";
	}
}
