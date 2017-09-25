package com.fdu.controller.factory;

import com.fdu.constants.ControllerType;
import com.fdu.controller.factory.impl.UserFactory;

public final class FactoryCreator {

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
