package com.hediapps.authentification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("authentificationService")
@Transactional
public class AuthentificationServiceImpl implements AuthentificationService {

	@Autowired
	private UserRepository userRepository;

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findOneByEmail(username);
	}

//	@Override
//	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
//		User user = userRepository.findOneByClientId(clientId);		
//		return user;
//	}

}
