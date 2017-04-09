package com.hediapps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hediapps.backend.dao.MessageDAOImpl;
import com.hediapps.backend.model.Message;

@Service
public class MessageService {

	@Autowired
	private MessageDAOImpl messageDAO;

	@Transactional
	public void create(Message t) {
		this.messageDAO.create(t);
	}

	@Transactional
	public Message readById(long id) {
		return this.messageDAO.findById(id);
	}

	@Transactional
	public void update(Message t) {
		this.messageDAO.update(t);
	}

	@Transactional
	public Message deleteById(Long id) {
		return this.messageDAO.delete(id);
	}

	@Transactional
	public List<Message> readAll() {
		return messageDAO.findAll();
	}
}
