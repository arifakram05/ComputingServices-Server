package com.fdu.database.factory;

import com.fdu.constants.ControllerType;
import com.fdu.database.factory.impl.UserFactory;

public class FactoryCreator {

	public static Factory getFactory(ControllerType controllerType) {
		switch (controllerType) {
		case USERS:
			return UserFactory.getInstance();

		case WIKI:
			break;

		default:
			break;
		}

		return null;
	}
}
