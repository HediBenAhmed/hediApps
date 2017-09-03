package com.hediapps.authentification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AuthentificationConfig {

	public @Bean BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
