package com.hediapps.backend.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dashboard")
public class Data {
	@Id
	private long id;

	private String name;
	private Map<String, Double> values;

	public Data() {

	}

	public Data(String name, Map<String, Double> values) {
		super();
		this.name = name;
		this.values = values;
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

	public Map<String, Double> getValues() {
		return values;
	}

	public void setValues(Map<String, Double> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "Data [id=" + id + ", name=" + name + ", value=" + values + "]";
	}
}
