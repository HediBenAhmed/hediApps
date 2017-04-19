package com.hediapps.config;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@Configuration
@PropertySource(value = { "classpath:/application.properties" })
public class BackEndMongoConfig {

//	@Value("${db.host}")
//	private String host;
//
//	@Value("${db.port}")
//	private int port;
//
//	@Value("${db.dbname}")
//	private String dbName;

	public @Bean MongoDbFactory mongoDbFactory() throws UnknownHostException {

		return new SimpleMongoDbFactory(new MongoClient("localhost", 27017), "hediapps");
	}

	public @Bean MongoTemplate mongoTemplate() throws UnknownHostException {

		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}

}