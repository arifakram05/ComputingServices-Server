package com.fdu.interfaces;

import org.apache.log4j.Logger;

import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.LabAssistant;
import com.fdu.model.User;

public interface ManagerService {

	final static Logger LOGGER = Logger.getLogger(ManagerService.class);

	boolean deleteJobApplicant(int studentId);

	default ComputingServicesResponse<Void> hire(LabAssistant labAssistant) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		if (hireJobApplicant(labAssistant)) {
			response.setStatusCode(200);
			response.setMessage(labAssistant.getStudentId()
					+ " hired successfully, you can now find the candidate list under lab assistants");
		} else {
			response.setStatusCode(404);
			response.setMessage(labAssistant.getStudentId() + " could not be hired. Operation unsuccessful");
		}
		return response;
	}

	boolean hireJobApplicant(LabAssistant labAssistant);

	boolean saveLabAssistant(LabAssistant labAssistant);

	/**
	 * Check if a user is authorized for system access
	 * 
	 * @param user
	 *            user to check
	 * @return <code>true</code> if user is already authorized,
	 *         <code>false</code> otherwise
	 */
	boolean isUserAuthorized(User user);

	/**
	 * Grant a user permissions to access system services
	 * 
	 * @param user
	 *            user to register
	 * @return {@link ComputingServicesResponse} containing response details
	 */
	ComputingServicesResponse<Void> authorizeUser(User user);

}
