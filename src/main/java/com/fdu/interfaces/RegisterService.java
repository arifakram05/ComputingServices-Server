package com.fdu.interfaces;

import org.apache.log4j.Logger;

import com.fdu.exception.ComputingServicesException;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.User;

public interface RegisterService {

	final static Logger LOGGER = Logger.getLogger(RegisterService.class);

	default ComputingServicesResponse<Void> registerUser(User user) {
		ComputingServicesResponse<User> response = new ComputingServicesResponse<>();
		try {
			register(user);
			LOGGER.info("User registered successfully " + user.getUserId());
			response.setStatusCode(200);
			response.setMessage("Registration success, now you can login");
		} catch (ComputingServicesException e) {
			LOGGER.error("Error occurred while registering ", e);
			response.setStatusCode(500);
			response.setMessage("Error occurred while registering");
		}
		return null;
	}

	void register(User user) throws ComputingServicesException;

	/**
	 * Check if the user can register.<br/>
	 * A user can register when a user's id is listed in 'users' collection
	 * 
	 * @param userId
	 *            user id
	 * @return {@link ComputingServicesResponse} with success status<br/>
	 *         	200 - if user can register<br/>
	 * 			404 - if user has already registered<br/>
	 *         	403 - if user is not authorized to register
	 */
	ComputingServicesResponse<Void> canUserRegister(String userId);

}
