package com.fdu.controller.factory.impl;

import com.fdu.controller.factory.Factory;
import com.fdu.controller.impl.UserControllerImpl;
import com.fdu.controller.interfaces.UserController;

public class UserFactory extends Factory {

	private UserFactory() {

	}

	@Override
	public UserController getUser() {
		return new UserControllerImpl();
	}

	private static class UserFactoryHelper {
		private static final UserFactory INSTANCE = new UserFactory();
	}

	public static UserFactory getInstance() {
		return UserFactoryHelper.INSTANCE;
	}

}
