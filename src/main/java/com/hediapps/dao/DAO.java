package com.hediapps.dao;

import java.util.List;

public interface DAO<T> {
	public T create(T object);

	public T update(T object);

	public T delete(long id);

	public T findById(long id);
	
	public List<T> findAll();
}