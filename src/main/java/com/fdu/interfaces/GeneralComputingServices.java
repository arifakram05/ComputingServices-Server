package com.fdu.interfaces;

import com.fdu.impl.GeneralComputingServicesImpl;
import com.fdu.impl.LoginServiceImpl;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.User;

public interface GeneralComputingServices extends Connection {

	/**
	 * Login a user to use system services
	 * 
	 * @param userDetails
	 *            user details to authenticate
	 * @return a {@link ComputingServicesResponse} containing {@link User} details
	 *         or failure details
	 */
	ComputingServicesResponse<User> login(String userDetails);

	/**
	 * Java 8 feature.<br/>
	 * Get an object of the implementations class
	 * 
	 * @return {@link GeneralComputingServicesImpl} Object of the class that implements this
	 *         interface
	 */
	static GeneralComputingServicesImpl getInstance() {
		return new GeneralComputingServicesImpl();
	}

	/*
	 * below methods provide instances of implementation classes
	 */

	default LoginService getLoginServiceInstance() {
		return new LoginServiceImpl(getDBConnection());
	}
}
