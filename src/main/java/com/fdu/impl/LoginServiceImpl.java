package com.fdu.impl;

import com.fdu.exception.ComputingServicesException;
import com.fdu.interfaces.LoginService;
import com.fdu.model.User;
import com.mongodb.client.MongoDatabase;

public class LoginServiceImpl implements LoginService {

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
	public User getUserDetails(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
