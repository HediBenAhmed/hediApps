package com.hediapps.authentification.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hediapps.authentification.service.AuthentificationService;

@Configuration
public class AuthenticationManagerConfiguration extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	private AuthentificationService authentificationService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.authentificationService).passwordEncoder(passwordEncoder);
	}
}