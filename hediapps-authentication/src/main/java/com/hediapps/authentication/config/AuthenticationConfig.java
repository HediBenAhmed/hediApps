package com.hediapps.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AuthenticationConfig {

	public @Bean BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
