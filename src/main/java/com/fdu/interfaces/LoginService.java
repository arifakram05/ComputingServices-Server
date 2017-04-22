package com.fdu.interfaces;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.log4j.Logger;

import com.fdu.exception.ComputingServicesException;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.User;
import com.fdu.util.SecureLogin;

/**
 * Handles services related to user login
 * 
 * @author arifakrammohammed
 *
 */
public interface LoginService {

	final static Logger LOGGER = Logger.getLogger(LoginService.class);
	
	/**
	 * Perform user login after input validation. If user is valid responds his
	 * request with a token.
	 * 
	 * @param user
	 *            user to login
	 * @return {@link ComputingServicesResponse} containing {@link User} details
	 * @throws NoSuchAlgorithmException
	 *             if the Secret key to validate a user against does not exist;
	 *             this happens when you restart the system, and user tries
	 *             logging in with old token
	 */
	default ComputingServicesResponse<User> login(User user) throws NoSuchAlgorithmException {
		ComputingServicesResponse<User> response = new ComputingServicesResponse<>();
		// 1. validate the input
		try {
			LOGGER.debug("Validating input for user login");
			validateInput(Long.parseLong(user.getUserId()));
			LOGGER.debug("Input validated - OK. Proceeding with login");
		} catch (ComputingServicesException e) {
			LOGGER.error("User Input Not Valid : " + user.getUserId(), e);
			// construct message with error details
			response.setStatusCode(404);
			response.setMessage("Login ID can only contain numbers");
			return response;
		}
		// 2. check if password is empty
		if(user.getPassword() == null || user.getPassword().isEmpty()) {
			LOGGER.info("Password should not be null or empty. User tried logging in with a hack "+user.getUserId());
			response.setStatusCode(404);
			response.setMessage("Password cannot be empty");
			return response;
		}
		// 3. check if user present in the system (with given user id and password)
		User userDetails = getUserDetails(user);
		if (userDetails != null) {
			LOGGER.debug("Associate login success "+user.getUserId());
			String authToken = SecureLogin.createJWT(Long.parseLong(user.getUserId()), -1);
			LOGGER.debug("JWT Token generated for User. User is granted system access");
			// construct response with Associate details and JWT token
			response.setAuthToken(authToken);
			response.setStatusCode(200);
			response.setMessage("Login Success");
			response.setResponse(Arrays.asList(userDetails));
		} else {
			// if given credentials are incorrect
			LOGGER.info("Either the login or password is incorrect "+user.getUserId());
			// construct message with error details
			response.setStatusCode(403);
			response.setMessage("Either the Id or password is incorrect. If you are a new user, please register then login");
		}
		return response;
	}

	/**
	 * check user id if per rules
	 * 
	 * @param userId
	 *            user id to validate
	 * @throws ComputingServicesException
	 *             if user input is not per set rules
	 */
	void validateInput(Long userId) throws ComputingServicesException;

	/**
	 * return the details of the user with given associate ID
	 * 
	 * @param user
	 *            associate whose details are to be retrieved
	 * @return {@link User} details
	 */
	User getUserDetails(User user);
}
