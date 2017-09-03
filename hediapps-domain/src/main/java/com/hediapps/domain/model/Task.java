package com.hediapps.domain.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dashboard")
public class Task {
	@Id
	private long id;

	private String name;
	private boolean done;
	private Date taskDate;

	public Task() {

	}

	public Task(String name, boolean done, Date taskDate) {
		super();
		this.name = name;
		this.done = done;
		this.taskDate = taskDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Date getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", done=" + done
				+ ", taskDate=" + taskDate + "]";
	}
}
