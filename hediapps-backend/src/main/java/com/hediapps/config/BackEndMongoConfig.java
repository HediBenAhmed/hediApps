package com.hediapps.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@PropertySource(value = { "classpath:/application.properties" })
public class BackEndMongoConfig extends AbstractMongoConfiguration {

	@Value("${db.host}")
	private String host;

	@Value("${db.port}")
	private int port;

	@Value("${db.dbname}")
	private String dbName;

	@Value("${db.username}")
	private String userName;

	@Value("${db.password}")
	private String password;

	@Override
	protected String getDatabaseName() {
		return this.dbName;
	}

	@Override
	public MongoClient mongo() {
		// Set credentials
		MongoCredential credential = MongoCredential.createCredential(userName,
				dbName, password.toCharArray());
		ServerAddress serverAddress = new ServerAddress(host, port);

		// Mongo Client
		return new MongoClient(serverAddress, Arrays.asList(credential));
	}

	@Override
	@Bean
	public MongoDbFactory mongoDbFactory() {

		// Mongo DB Factory
		SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(
				mongo(), dbName);

		return simpleMongoDbFactory;
	}

	@Override
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {

		return new MongoTemplate(mongoDbFactory());
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}