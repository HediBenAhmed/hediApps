package com.hediapps.service;

import java.util.List;

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
		this.dataDAO.create(t);
	}

	@Transactional
	public Data readById(long id) {
		return this.dataDAO.findById(id);
	}

	@Transactional
	public void update(Data t) {
		this.dataDAO.update(t);
	}

	@Transactional
	public Data deleteById(Long id) {
		return this.dataDAO.delete(id);
	}

	@Transactional
	public List<Data> readAll() {
		return dataDAO.findAll();
	}
}
