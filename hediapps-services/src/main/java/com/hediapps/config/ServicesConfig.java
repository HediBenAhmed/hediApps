package com.hediapps.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.hediapps.service.DataService;
import com.hediapps.service.EventService;
import com.hediapps.service.MessageService;
import com.hediapps.service.TaskService;
import com.hediapps.service.UserService;

@Configuration
public class ServicesConfig {

	public @Bean EventService eventService() {
		return new EventService();
	}

	public @Bean TaskService taskService() {
		return new TaskService();
	}

	public @Bean DataService dataService() {
		return new DataService();
	}

	public @Bean MessageService messageService() {
		return new MessageService();
	}

	public @Bean UserService userService() {
		return new UserService();
	}

	public @Bean BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
