package com.fdu.database.factory.impl;

import com.fdu.database.DBConnection;
import com.fdu.database.dao.UserDAO;
import com.fdu.database.daoimpl.UserDAOImpl;
import com.fdu.database.factory.Factory;

public class UserFactory extends Factory {

	private UserFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserDAO getUser() {
		return new UserDAOImpl(DBConnection.getConnection());
	}

	private static class UserFactoryHelper {
		private static final UserFactory INSTANCE = new UserFactory();
	}

	public static UserFactory getInstance() {
		return UserFactoryHelper.INSTANCE;
	}

}
