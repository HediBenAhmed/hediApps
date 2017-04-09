package com.hediapps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hediapps.backend.dao.TaskDAOImpl;
import com.hediapps.backend.model.Task;

@Service
public class TaskService {

	@Autowired
	private TaskDAOImpl taskDAO;

	@Transactional
	public void create(Task t) {
		this.taskDAO.create(t);
	}

	@Transactional
	public Task readById(long id) {
		return this.taskDAO.findById(id);
	}

	@Transactional
	public void update(Task t) {
		this.taskDAO.update(t);
	}

	@Transactional
	public Task deleteById(Long id) {
		return this.taskDAO.delete(id);
	}

	@Transactional
	public List<Task> readAll() {
		return taskDAO.findAll();
	}
}
