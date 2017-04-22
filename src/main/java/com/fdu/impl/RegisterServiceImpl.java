package com.fdu.impl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.codehaus.jackson.map.ObjectMapper;

import com.fdu.constants.Constants;
import com.fdu.exception.ComputingServicesException;
import com.fdu.interfaces.RegisterService;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.User;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class RegisterServiceImpl implements RegisterService {

	private final static Logger LOGGER = Logger.getLogger(RegisterServiceImpl.class);

	MongoDatabase database;

	public RegisterServiceImpl(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public void register(User user) throws ComputingServicesException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ComputingServicesResponse<Void> canUserRegister(String userId) {
		// get collection
		MongoCollection<Document> usersCollection = database.getCollection(Constants.USERS.getValue());
		// response
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		// process each retrieved record
		Block<Document> processRetreivedData = (document) -> {

			User user;
			String retrivedDataAsJSON = document.toJson();
			try {
				user = new ObjectMapper().readValue(retrivedDataAsJSON, User.class);
				if (user.getPassword() == null) {
					response.setStatusCode(200);
					LOGGER.info(userId+" is authorized to register");
				}
				else {
					response.setStatusCode(404);
					LOGGER.info(userId+" is already registered");
				}
			} catch (IOException e) {
				LOGGER.error("Error occurred while processing retrieved associate details for search associates operation");
			}
		};
		// query
		usersCollection.find(Filters.eq(Constants.USERID.getValue(), userId)).forEach(processRetreivedData);
		if (response.getStatusCode() == 0) {
			response.setStatusCode(403);
			LOGGER.info(userId+" is NOT authorized to register");
		}
		return response;
	}

}
