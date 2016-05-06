package com.hediapps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hediapps.dao.UserDAOImpl;
import com.hediapps.model.User;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserDAOImpl userDAO;

	public void create(User t) {
		this.userDAO.create(t);
	}

	public User readById(long id) {
		return this.userDAO.findById(id);
	}

	public User findByUserNameAndPassword(String username, String password) {

		String encryptedPwd = encrypt(password);
		return this.userDAO.findByUserNameAndPassword(username, encryptedPwd);
	}

	public void update(User t) {
		this.userDAO.update(t);
	}

	public User deleteById(Long id) {
		return this.userDAO.delete(id);
	}

	public List<User> readAll() {
		return userDAO.findAll();
	}

	private String encrypt(String password) {

		if (password != null)
			return password.toUpperCase();
		else
			return null;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = this.userDAO.findByUserName(username);
		
		if (null == user) {
			throw new UsernameNotFoundException("The user with name " + username + " was not found");
		}

		return user;
	}
}
