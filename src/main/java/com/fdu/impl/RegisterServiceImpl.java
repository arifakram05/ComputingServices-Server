package com.fdu.impl;

import com.fdu.exception.ComputingServicesException;
import com.fdu.interfaces.RegisterService;
import com.fdu.model.User;
import com.mongodb.client.MongoDatabase;

public class RegisterServiceImpl implements RegisterService {

	MongoDatabase database;

	public RegisterServiceImpl(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public void register(User user) throws ComputingServicesException {
		// TODO Auto-generated method stub
		
	}

}
