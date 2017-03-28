package com.fdu.interfaces;

import com.fdu.impl.GeneralOperationsImpl;
import com.fdu.impl.LoginServiceImpl;
import com.fdu.impl.RegisterServiceImpl;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.User;

public interface GeneralOperations extends Connection {

	/**
	 * Login a user to use system services
	 * 
	 * @param userDetails
	 *            user details to authenticate
	 * @return a {@link ComputingServicesResponse} containing {@link User}
	 *         details or failure details
	 */
	ComputingServicesResponse<User> login(String userDetails);

	/**
	 * Register a new user
	 * 
	 * @param userDetails
	 *            new user details
	 * @return {@link ComputingServicesResponse} containing response details
	 */
	ComputingServicesResponse<Void> register(String userDetails);

	/**
	 * Java 8 feature.<br/>
	 * Get an object of the implementations class
	 * 
	 * @return {@link GeneralOperationsImpl} Object of the class that
	 *         implements this interface
	 */
	static GeneralOperationsImpl getInstance() {
		return new GeneralOperationsImpl();
	}

	/*
	 * below methods provide instances of implementation classes
	 */

	default LoginService getLoginServiceInstance() {
		return new LoginServiceImpl(getDBConnection());
	}

	default RegisterService getRegisterServiceInstance() {
		return new RegisterServiceImpl(getDBConnection());
	}

}
