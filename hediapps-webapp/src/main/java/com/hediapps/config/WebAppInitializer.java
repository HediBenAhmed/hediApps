package com.hediapps.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	  @Override
	  protected Class<?>[] getRootConfigClasses() {
	    return new Class<?>[] { AppConfig.class, WebSocketConfig.class };
	  }

	  @Override
	  protected Class<?>[] getServletConfigClasses() {
	    return new Class<?>[] { WebConfig.class };
	  }

	  @Override
	  protected String[] getServletMappings() {
	    return new String[] { "/" };
	  }

//	  @Override
//	  protected Filter[] getServletFilters() {
//	    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//	    characterEncodingFilter.setEncoding(StandardCharsets.UTF_8.name());
//	    return new Filter[] { characterEncodingFilter };
//	  }
	}