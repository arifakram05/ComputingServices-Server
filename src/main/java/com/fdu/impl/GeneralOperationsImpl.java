package com.fdu.impl;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.fdu.interfaces.GeneralOperations;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.JobApplicant;
import com.fdu.model.User;

public class GeneralOperationsImpl implements GeneralOperations {

	private final static Logger LOGGER = Logger.getLogger(GeneralOperationsImpl.class);

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

	@Override
	public ComputingServicesResponse<Void> register(String userDetails) {
		ComputingServicesResponse<Void> response = null;
		User user = null;
		try {
			user = new ObjectMapper().readValue(userDetails, User.class);
			LOGGER.info("Registering new user " + user.getUserId());
			response = getRegisterServiceInstance().registerUser(user);
			LOGGER.info("Registering user " + user.getUserId() + " success");
		} catch (Exception e) {
			LOGGER.error("Error while registering new user " + user.getUserId(), e);
			response = new ComputingServicesResponse<>();
			response.setStatusCode(500);
			response.setMessage("Error occurred while registering you. Please contact Lab Manager");
		}
		return response;
	}

	@Override
	public ComputingServicesResponse<Void> careers(String application, Object resume) {
		ComputingServicesResponse<Void> response = null;
		JobApplicant jobApplicant = null;
		try {
			jobApplicant = new ObjectMapper().readValue(application, JobApplicant.class);
			jobApplicant.setResume(IOUtils.toByteArray((InputStream) resume));
			LOGGER.info("Recording a new job application " + jobApplicant.getStudentId());
			response = getCareersServiceInstance().careers(jobApplicant);
			LOGGER.info("Save Job applicant details " + jobApplicant.getStudentId() + " success");
		} catch (Exception e) {
			LOGGER.error("Error while saving job applicant details " + jobApplicant.getStudentId(), e);
			response = new ComputingServicesResponse<>();
			response.setStatusCode(500);
			response.setMessage("Error Occurred while registering you");
		}
		return response;
	}

	@Override
	public ComputingServicesResponse<Void> canUserRegister(String userId) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		try {
			LOGGER.info("Preparing to check if a user can register "+userId);
			response = getRegisterServiceInstance().canUserRegister(userId);
			if (response.getStatusCode() == 200) {
				response.setMessage("You are authorized to register, please continue");
			} else if (response.getStatusCode() == 403) {
				response.setMessage("You are not authorized to register. Please contact Lab Assistant or Lab Manager");
			} else if (response.getStatusCode() == 404) {
				response.setMessage("You are already registered, please continue to login");
			}
		} catch (Exception e) {
			LOGGER.error("Error occurred while verifying ", e);
			response.setStatusCode(500);
			response.setMessage("Error occurred while verifying. Please contact Lab Assistant or Lab Manager");
		}
		LOGGER.info("User Registration check for complete for "+userId);
		return response;
	}

	@Override
	public ComputingServicesResponse<User> searchUsers(String searchText) {
		ComputingServicesResponse<User> response = new ComputingServicesResponse<>();
		List<User> userList = null;
		try {
			LOGGER.info("Preparing to search for users with pattern: "+searchText);
			userList = getManagerServiceInstance().searchUsers(searchText);
			LOGGER.info("Users searched and retrieved number is "+userList.size());
			response.setStatusCode(200);
			response.setMessage("List of all users that match search criteria");
			response.setResponse(userList);
		} catch (Exception e) {
			LOGGER.error("Error while fetching users per search criteria", e);
			response.setStatusCode(500);
			response.setMessage("Error occurred. Could not retrieve users per your search criteria");
		}
		return response;
	}

}
