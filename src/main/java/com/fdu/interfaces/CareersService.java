package com.fdu.interfaces;

import java.util.List;

import org.apache.log4j.Logger;

import com.fdu.exception.ComputingServicesException;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.JobApplicant;

/**
 * Handles services related to job as a lab assistant
 * 
 * @author arifakrammohammed
 *
 */
public interface CareersService {

	final static Logger LOGGER = Logger.getLogger(CareersService.class);

	default ComputingServicesResponse<Void> careers(JobApplicant jobApplicant) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		// 1. Validate input
		try {
			LOGGER.debug("Validating input before saving a job applicant");
			validateInput(jobApplicant);
			LOGGER.debug("Input validated. Proceeding with processing request");
		} catch (ComputingServicesException e) {
			LOGGER.error("User Input Not Valid ", e);
			// construct message with error details
			response.setStatusCode(404);
			response.setMessage("Please ensure that your input does not have these characters [ <> \" ! \' : { } ]");
			return response;
		}
		// 2. as input is valid, proceed with saving
		saveJobApplicant(jobApplicant);
		response.setStatusCode(200);
		response.setMessage("You have applied for the position of Lab Assistant. Good Luck");
		return response;
	}

	void validateInput(JobApplicant jobApplicant) throws ComputingServicesException;

	/**
	 * Save the details of a student who is applying for the Lab Assistant
	 * position
	 * 
	 * @param jobApplicant
	 *            student details
	 */
	void saveJobApplicant(JobApplicant jobApplicant);

	/**
	 * Get the list of all job applicants
	 * 
	 * @return {@link List} of {@link JobApplicant}s
	 */
	List<JobApplicant> viewAllJobApplicants();

	/**
	 * Get the given student's job application status
	 * 
	 * @param studentId
	 *            student Id
	 * @return String indicating the status of the job applicant
	 */
	String getJobApplicantStatus(String studentId);
}
