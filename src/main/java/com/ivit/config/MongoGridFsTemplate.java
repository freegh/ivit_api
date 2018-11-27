package com.ivit.config;

import java.net.UnknownHostException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
public class MongoGridFsTemplate  extends AbstractMongoConfiguration{
	
	@Value("${spring.data.mongodb.host}")
	private String mongoAddress; 
	@Value("${spring.data.mongodb.port}")
	private Integer mongoPort;
	@Value("${spring.data.mongodb.database}")
	private String mongoDatabase;
	
	@Value("${spring.data.mongodb.authentication-database}")
	private String authenticationDB;
	@Value("${spring.data.mongodb.username}")
	private String username;
	@Value("${spring.data.mongodb.password}")
	private String password;
	@Value("${spring.data.mongodb.database}")
	private String database;	
	
	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {
	    return new GridFsTemplate(new SimpleMongoDbFactory(
				new MongoClient(new ServerAddress(mongoAddress, mongoPort),
						Collections.singletonList(
								MongoCredential.createCredential(username, authenticationDB, password.toCharArray()))),
				database), mappingMongoConverter());
	}
	
	@Override
	protected String getDatabaseName() {
		return mongoDatabase;
	}

	@Override
	public MongoClient mongoClient() {
		return new MongoClient(mongoAddress);
	}

}