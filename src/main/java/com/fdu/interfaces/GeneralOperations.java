package com.fdu.interfaces;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fdu.impl.CareersServiceImpl;
import com.fdu.impl.GeneralOperationsImpl;
import com.fdu.impl.LoginServiceImpl;
import com.fdu.impl.ManagerServiceImpl;
import com.fdu.impl.RegisterServiceImpl;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.User;

public interface GeneralOperations extends Connection {

	Map<String, Object> instanceMap = new ConcurrentHashMap<>();

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

	ComputingServicesResponse<Void> canUserRegister(String userId);

	ComputingServicesResponse<User> searchUsers(String searchText);

	/**
	 * Check status of a job applicant
	 * 
	 * @param studentId
	 * @return {@link ComputingServicesResponse} containing response details
	 */
	ComputingServicesResponse<Void> checkJobApplicantStatus(String studentId);

	/**
	 * Apply for a job as a lab assistant
	 * 
	 * @param application
	 *            job applicant details
	 * @param resume
	 *            job applicant resume
	 * @return {@link ComputingServicesResponse} containing response details
	 */
	ComputingServicesResponse<Void> careers(String application, Object resume);

	/**
	 * Java 8 feature.<br/>
	 * Get an object of the implementations class
	 * 
	 * @return {@link GeneralOperationsImpl} Object of the class that implements
	 *         this interface
	 */
	static GeneralOperationsImpl getInstance() {
		if (instanceMap.get("GeneralOperations") == null) {
			instanceMap.put("GeneralOperations", new GeneralOperationsImpl());
		}
		return (GeneralOperationsImpl) instanceMap.get("GeneralOperations");
	}

	/*
	 * below methods provide instances of implementation classes
	 */

	default LoginService getLoginServiceInstance() {
		if (instanceMap.get("LoginService") == null) {
			instanceMap.put("LoginService", new LoginServiceImpl(getDBConnection()));
		}
		return (LoginServiceImpl) instanceMap.get("LoginService");
	}

	default RegisterService getRegisterServiceInstance() {
		if (instanceMap.get("RegisterService") == null) {
			instanceMap.put("RegisterService", new RegisterServiceImpl(getDBConnection()));
		}
		return (RegisterServiceImpl) instanceMap.get("RegisterService");
	}

	default CareersService getCareersServiceInstance() {
		if (instanceMap.get("CareersService") == null) {
			instanceMap.put("CareersService", new CareersServiceImpl(getDBConnection()));
		}
		return (CareersServiceImpl) instanceMap.get("CareersService");
	}

	default ManagerService getManagerServiceInstance() {
		if (instanceMap.get("ManagerService") == null) {
			instanceMap.put("ManagerService", new ManagerServiceImpl(getDBConnection()));
		}
		return (ManagerServiceImpl) instanceMap.get("ManagerService");
	}

}
