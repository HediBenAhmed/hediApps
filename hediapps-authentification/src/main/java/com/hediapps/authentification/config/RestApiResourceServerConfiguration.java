package com.hediapps.authentification.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

//@Configuration
//@EnableResourceServer
public class RestApiResourceServerConfiguration {/*
													 * extends
													 * ResourceServerConfigurerAdapter
													 * {
													 * 
													 * public static final
													 * String RESOURCE_ID =
													 * "hediapps";
													 * 
													 * @Override public void
													 * configure(
													 * ResourceServerSecurityConfigurer
													 * resources) {
													 * resources.resourceId(
													 * RESOURCE_ID); }
													 * 
													 * @Override public void
													 * configure(HttpSecurity
													 * http) throws Exception {
													 * http.authorizeRequests().
													 * anyRequest().permitAll();
													 * }
													 */
}
