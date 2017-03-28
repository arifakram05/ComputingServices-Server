package com.fdu.impl;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.fdu.interfaces.GeneralComputingServices;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.User;

public class GeneralComputingServicesImpl implements GeneralComputingServices {

	private final static Logger LOGGER = Logger.getLogger(GeneralComputingServicesImpl.class);

	@Override
	public ComputingServicesResponse<User> login(String userDetails) {
		ComputingServicesResponse<User> response = null;
		User user = null;
		try {
			user = new ObjectMapper().readValue(userDetails, User.class);
			LOGGER.info("Logging in " + user.getUserId());
			response = getLoginServiceInstance().login(user);
			LOGGER.info("Logging in " + user.getUserId() + " success");
		} catch (Exception e) {
			LOGGER.error("Error while logging in the user " + user.getUserId(), e);
			response = new ComputingServicesResponse<>();
			response.setStatusCode(500);
			response.setMessage("Error Occurred while logging you in");
		}
		return response;
	}

}
