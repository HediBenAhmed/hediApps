package com.hediapps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hediapps.backend.dao.EventDAOImpl;
import com.hediapps.backend.model.Event;

@Service
public class EventService {
	@Autowired
	private EventDAOImpl eventDAO;

	@Transactional
	public void create(Event t) {
		this.eventDAO.create(t);
	}

	@Transactional
	public Event readById(long id) {
		return this.eventDAO.findById(id);
	}

	@Transactional
	public void update(Event t) {
		this.eventDAO.update(t);
	}

	@Transactional
	public Event deleteById(Long id) {
		return this.eventDAO.delete(id);
	}

	@Transactional
	public List<Event> readAll() {
		return eventDAO.findAll();
	}
}