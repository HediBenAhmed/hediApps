package com.hediapps.messaging.model;

import java.util.Date;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "common")
public class MessageEntity {

	@Id
	private long id;

	private long creationDate;
	private String text;

	private boolean read;

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate == null ? 0l : creationDate.getTime();
	}
}
