package com.hediapps.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "common")
public class SystemEvent extends Event {

	private String event;
	private EventSeverity severity;
	private long eventDate;

	public SystemEvent(long id, String event, EventSeverity severity, long eventDate) {
		super();
		this.setId(id);
		this.event = event;
		this.severity = severity;
		this.eventDate = eventDate;
	}

	public SystemEvent() {
		super();
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public EventSeverity getSeverity() {
		return severity;
	}

	public void setSeverity(EventSeverity severity) {
		this.severity = severity;
	}

	public long getEventDate() {
		return eventDate;
	}

	public void setEventDate(long eventDate) {
		this.eventDate = eventDate;
	}

}
