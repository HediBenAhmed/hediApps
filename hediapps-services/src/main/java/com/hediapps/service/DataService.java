package com.hediapps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hediapps.backend.dao.DataDAOImpl;
import com.hediapps.backend.model.Data;


@Service
public class DataService {

	@Autowired
	private DataDAOImpl dataDAO;

	@Transactional
	public void create(Data t) {
		this.dataDAO.save(t);
	}

	@Transactional
	public Data readById(long id) {
		return this.dataDAO.findOne(id);
	}

	@Transactional
	public void update(Data t) {
		this.dataDAO.save(t);
	}

	@Transactional
	public Data deleteById(Long id) {
		this.dataDAO.delete(id);
		return null;
	}

	@Transactional
	public Iterable<Data> readAll() {
		return dataDAO.findAll();
	}
	
	@Transactional
	public Iterable<Data> findByName(String name) {
		return dataDAO.searchByName(name);
	}
}
