package com.hediapps.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hediapps.backend.dao.MessageDAOImpl;
import com.hediapps.backend.dao.UserDAOImpl;
import com.hediapps.backend.model.Message;
import com.hediapps.backend.model.User;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserDAOImpl userDAO;

	@Autowired
	private MessageDAOImpl messageDAO;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public void create(User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		this.userDAO.create(user);
	}

	public User readById(long id) {
		return this.userDAO.findById(id);
	}

	public void update(User user) {
		this.userDAO.update(user);
	}

	public User deleteById(Long id) {
		return this.userDAO.delete(id);
	}

	public List<User> readAll() {
		return userDAO.findAll();
	}

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = this.userDAO.findByEmail(email);

		if (null == user) {
			throw new UsernameNotFoundException("The user with name " + email + " was not found");
		}

		return user;
	}

	public List<Message> getMessages(Long userId) {
		List<Message> messages = messageDAO.findByToUserId(userId);
		return messages;
	}

	public List<Message> getMessages(Long userId, boolean isRead) {
		List<Message> messages = getMessages(userId);

		if (messages != null && !messages.isEmpty()) {
			List<Message> filtredMessages = new ArrayList<Message>();
			for (Message msg : messages) {
				if (msg.isRead() == isRead) {
					filtredMessages.add(msg);
				}
			}

			return filtredMessages;
		}
		return messages;
	}
}
