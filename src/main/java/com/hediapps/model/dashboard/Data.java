package com.hediapps.model.dashboard;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dashboard")
public class Data {
	@Id
	private long id;

	private String name;
	private List<Double> values;

	public Data() {

	}

	public Data(String name, List<Double> values) {
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

	public List<Double> getValues() {
		return values;
	}

	public void setValues(List<Double> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "Data [id=" + id + ", name=" + name + ", value=" + values + "]";
	}
}
