package com.fdu.interfaces;

import java.util.List;

import org.apache.log4j.Logger;

import com.fdu.exception.ComputingServicesException;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.LabAssistant;
import com.fdu.model.User;

public interface ManagerService {

	final static Logger LOGGER = Logger.getLogger(ManagerService.class);

	boolean deleteJobApplicant(String studentId);

	default ComputingServicesResponse<Void> hire(LabAssistant labAssistant) throws ComputingServicesException {
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

	boolean hireJobApplicant(LabAssistant labAssistant) throws ComputingServicesException;

	boolean updateJobApplicantStatus(String status, String studentId);

	boolean saveLabAssistant(LabAssistant labAssistant);

	boolean updateLabApplicantStatus(String status, String studentId);

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

	List<User> searchUsers(String searchText);

}
