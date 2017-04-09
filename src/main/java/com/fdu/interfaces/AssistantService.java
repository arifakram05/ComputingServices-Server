package com.fdu.interfaces;

import java.util.List;

import org.apache.log4j.Logger;

import com.fdu.exception.ComputingServicesException;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.LabAssistant;

public interface AssistantService {

	final static Logger LOGGER = Logger.getLogger(AssistantService.class);

	/**
	 * Get the list of all lab assistants
	 * 
	 * @return {@link List} of {@link LabAssistant}s
	 */
	List<LabAssistant> viewAllLabAssistants();

	/**
	 * Delete a given lab assistant from DB records.
	 * 
	 * @param studentId
	 *            LA to be deleted
	 * @return boolean indicating the success status of the operation
	 */
	boolean deleteLabAssistant(int studentId);

	/**
	 * Update a given lab assistant
	 * 
	 * @param labAssistant
	 *            LA whose details are to be updated
	 * @return {@link ComputingServicesResponse} containing success status of
	 *         the operation
	 */
	default ComputingServicesResponse<Void> updateLabAssistant(LabAssistant labAssistant) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		try {
			updateLA(labAssistant);
			response.setStatusCode(200);
			response.setMessage(
					"Successfully updated details for lab assistant with ID " + labAssistant.getStudentId());
		} catch (ComputingServicesException e) {
			LOGGER.error("Error occurred while updating lab assistant " + labAssistant.getStudentId(), e);
			response.setStatusCode(500);
			response.setMessage("Error occurred while updating lab assistant with ID " + labAssistant.getStudentId());
		}
		return response;
	}

	/**
	 * Update a given lab assistant
	 * 
	 * @param labAssistant
	 *            LA whose details are to be updated
	 * 
	 */
	void updateLA(LabAssistant labAssistant) throws ComputingServicesException;

	/**
	 * Update a given lab assistant's profile
	 * 
	 * @param labAssistant
	 *            lab assistant details to update
	 * @return {@link ComputingServicesResponse} containing success status of
	 *         the operation
	 */
	void updateLAProfile(LabAssistant labAssistant)
			throws ComputingServicesException;

}
