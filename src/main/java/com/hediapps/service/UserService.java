package com.hediapps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hediapps.dao.UserDAOImpl;
import com.hediapps.model.User;

@Service
public class UserService {

	@Autowired
	private UserDAOImpl userDAO;

	@Transactional
	public void create(User t) {
		this.userDAO.create(t);
	}

	@Transactional
	public User readById(long id) {
		return this.userDAO.findById(id);
	}

	@Transactional
	public User findByLoinAndPassword(String login, String password) {

		String encryptedPwd = encrypt(password);
		return this.userDAO.findByLoinAndPassword(login, encryptedPwd);
	}

	@Transactional
	public void update(User t) {
		this.userDAO.update(t);
	}

	@Transactional
	public User deleteById(Long id) {
		return this.userDAO.delete(id);
	}

	@Transactional
	public List<User> readAll() {
		return userDAO.findAll();
	}

	private String encrypt(String password) {

		if (password != null)
			return password.toUpperCase();
		else
			return null;
	}
}
