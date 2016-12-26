package com.hediapps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hediapps.dao.NotificationDAOImpl;
import com.hediapps.model.Notification;

@Service
public class NotificationService {
	@Autowired
	private NotificationDAOImpl notificationDAO;

	@Transactional
	public void create(Notification t) {
		this.notificationDAO.create(t);
	}

	@Transactional
	public Notification readById(long id) {
		return this.notificationDAO.findById(id);
	}

	@Transactional
	public void update(Notification t) {
		this.notificationDAO.update(t);
	}

	@Transactional
	public Notification deleteById(Long id) {
		return this.notificationDAO.delete(id);
	}

	@Transactional
	public List<Notification> readAll() {
		return notificationDAO.findAll();
	}
}