package com.fdu.interfaces;

import java.util.List;

import org.apache.log4j.Logger;

import com.fdu.exception.ComputingServicesException;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.LabAssistant;
import com.fdu.model.StaffSchedule;

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
	boolean deleteLabAssistant(String studentId);

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
	void updateLAProfile(LabAssistant labAssistant) throws ComputingServicesException;

	Object download(String id, String requester) throws ComputingServicesException;

	/**
	 * Get assigned work schedule of a given user for the given date.
	 * 
	 * @param studentId
	 *            student Id
	 * @param date
	 *            date in String format MMM dd, yyyy HH:mm
	 * @return {@link List} of {@link StaffSchedule} containing all assigned
	 *         shifts for given user for the given date
	 * @throws ComputingServicesException
	 *             {@link ComputingServicesException} in case of error during
	 *             processing
	 */
	List<StaffSchedule> getShiftSchedule(String studentId, String date) throws ComputingServicesException;

	/**
	 * Record time sheet of the given user.
	 * 
	 * @param operation
	 *            clocking-in or clocking-out
	 * @param studentId
	 *            student Id
	 * @param datetime
	 *            date and time of the operation in MMM dd, yyyy HH:mm format
	 * @param id
	 *            Id of the record to update
	 */
	void saveTimesheet(String operation, String studentId, String datetime, String id)
			throws ComputingServicesException;

	/**
	 * Get assigned work schedule of a given user for the given two dates.
	 * 
	 * @param studentId
	 *            student Id
	 * @param startDate
	 *            start date in String format MMM dd, yyyy HH:mm
	 * @param endDate
	 *            end date in String format MMM dd, yyyy HH:mm
	 * @return {@link List} of {@link StaffSchedule} containing all assigned
	 *         shifts for given user for the given dates
	 * @throws ComputingServicesException
	 *             {@link ComputingServicesException} in case of error during
	 *             processing
	 */
	List<StaffSchedule> getShiftSchedule(String studentId, String startDate, String endDate)
			throws ComputingServicesException;

}
