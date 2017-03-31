package com.fdu.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.fdu.interfaces.ManagerOperations;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.JobApplicant;
import com.fdu.model.LabAssistant;

public class ManagerOperationsImpl implements ManagerOperations {

	private final static Logger LOGGER = Logger.getLogger(ManagerOperationsImpl.class);

	@Override
	public ComputingServicesResponse<JobApplicant> viewJobApplicants() {
		ComputingServicesResponse<JobApplicant> response = new ComputingServicesResponse<>();
		List<JobApplicant> jobApplicantList = null;
		try {
			LOGGER.info("Preparing to fetch all job applicants");
			jobApplicantList = getCareersServiceInstance().viewAllJobApplicants();
			LOGGER.info("All job applicants successfully retrieved");
			response.setStatusCode(200);
			response.setMessage("List of all Job Applicants");
			response.setResponse(jobApplicantList);
		} catch (Exception e) {
			LOGGER.error("Error while fetching all job applicants ", e);
			response.setStatusCode(500);
			response.setMessage("Error occurred. Could not get job applicant list");
		}
		return response;
	}

	@Override
	public ComputingServicesResponse<LabAssistant> viewLabAssistants() {
		ComputingServicesResponse<LabAssistant> response = new ComputingServicesResponse<>();
		List<LabAssistant> labAssistantList = null;
		try {
			LOGGER.info("Preparing to fetch all lab assistants");
			labAssistantList = getAssistantServiceInstance().viewAllLabAssistants();
			LOGGER.info("All lab assistants successfully retrieved");
			response.setStatusCode(200);
			response.setMessage("List of all lab assistants");
			response.setResponse(labAssistantList);
		} catch (Exception e) {
			LOGGER.error("Error while fetching all lab assistants ", e);
			response.setStatusCode(500);
			response.setMessage("Error occurred. Could not get lab assistant list");
		}
		return response;
	}

}
