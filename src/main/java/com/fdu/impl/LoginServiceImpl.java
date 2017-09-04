package com.fdu.impl;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.codehaus.jackson.map.ObjectMapper;

import com.fdu.constants.Constants;
import com.fdu.exception.ComputingServicesException;
import com.fdu.interfaces.LoginService;
import com.fdu.model.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class LoginServiceImpl implements LoginService {

	private final static Logger LOGGER = Logger.getLogger(LoginServiceImpl.class);

	MongoDatabase database;

	public LoginServiceImpl(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public void validateInput(Long userId) throws ComputingServicesException {
		// TODO Auto-generated method stub

	}

	@Override
	public User getUserDetails(User user) throws ComputingServicesException {
		// get collection
		MongoCollection<Document> usersCollection = database.getCollection(Constants.USERS.getValue());
		// query
		Document resultFromDB = usersCollection
				.find(and(eq(Constants.USERID.getValue(), user.getUserId()),
						eq(Constants.PASSWORD.getValue(), user.getPassword()))).first();
		if (resultFromDB == null) {
			return null;
		}
		User userData = null;
		try {
			userData = new ObjectMapper().readValue(resultFromDB.toJson(), User.class);
		} catch (IOException e) {
			LOGGER.error("Error while fetching user data ", e);
			throw new ComputingServicesException(e);
		}
		return userData;
	}

}
