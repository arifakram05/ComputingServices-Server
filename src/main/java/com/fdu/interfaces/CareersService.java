package com.fdu.interfaces;

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

	default ComputingServicesResponse<Void> careeers(JobApplicant jobApplicant) {
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

	void saveJobApplicant(JobApplicant jobApplicant);

}
