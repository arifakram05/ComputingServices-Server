package com.fdu.controller.impl;

import java.util.List;

import com.fdu.constants.ControllerType;
import com.fdu.controller.interfaces.UserController;
import com.fdu.database.factory.FactoryCreator;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.User;
import com.fdu.util.GenericUtility;

public class UserControllerImpl implements UserController {

	@Override
	public ComputingServicesResponse<User> get() {
		List<User> users = FactoryCreator.getFactory(ControllerType.USERS).getUser().get();
		return GenericUtility.createSuccessResponse("All users retrieved.", users);
	}

	@Override
	public ComputingServicesResponse<Void> blockUser(String id) {
		String message = null;
		if (FactoryCreator.getFactory(ControllerType.USERS).getUser().blockUser(id)) {
			message = "Blocked " + id;
			return GenericUtility.createSuccessResponse(message);
		} else {
			message = "Failed to block " + id;
			return GenericUtility.createFailureResponse(message);
		}
	}

	@Override
	public ComputingServicesResponse<Void> unblockUser(String id) {
		String message = null;
		if (FactoryCreator.getFactory(ControllerType.USERS).getUser().unblockUser(id)) {
			message = "UnBlocked " + id;
			return GenericUtility.createSuccessResponse(message);
		} else {
			message = "Failed to unblock " + id;
			return GenericUtility.createFailureResponse(message);
		}
	}

	@Override
	public ComputingServicesResponse<Void> deleteUser(String id) {
		String message = null;
		if (FactoryCreator.getFactory(ControllerType.USERS).getUser().deleteUser(id)) {
			message = "Deleted " + id;
			return GenericUtility.createSuccessResponse(message);
		} else {
			message = "Failed to delete " + id;
			return GenericUtility.createFailureResponse(message);
		}
	}

	@Override
	public ComputingServicesResponse<Void> addUser(User user) {
		String message = null;
		try {
			FactoryCreator.getFactory(ControllerType.USERS).getUser().addUser(user);
			message = "Added Successfully";
			return GenericUtility.createSuccessResponse(message);
		} catch (Exception exception) {
			message = "Failed to add new user";
			return GenericUtility.createFailureResponse(message);
		}
	}
}
