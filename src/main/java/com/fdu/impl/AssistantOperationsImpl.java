package com.fdu.impl;

import java.io.InputStream;

import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.fdu.interfaces.AssistantOperations;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.LabAssistant;
import com.fdu.model.StaffSchedule;

public class AssistantOperationsImpl implements AssistantOperations {

	private final static Logger LOGGER = Logger.getLogger(AssistantOperationsImpl.class);

	@Override
	public ComputingServicesResponse<Void> updateProfile(String labassistant, Object resume, Object photo) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		LabAssistant labAssistant = null;
		try {
			labAssistant = new ObjectMapper().readValue(labassistant, LabAssistant.class);
			labAssistant.setResume(IOUtils.toByteArray((InputStream) resume));
			labAssistant.setPhoto(IOUtils.toByteArray((InputStream) photo));
			LOGGER.info(
					"Updating lab assistant profile " + labAssistant.getLastName() + "," + labAssistant.getFirstName());
			getAssistantServiceInstance().updateLAProfile(labAssistant);
			LOGGER.info("Updating lab assistant profile success");
			response.setStatusCode(200);
			response.setMessage("Updated");
		} catch (Exception e) {
			LOGGER.error("Error while updating lab assistant details " + labAssistant.getLastName() + ","
					+ labAssistant.getFirstName(), e);
			response.setStatusCode(500);
			response.setMessage("Error Occurred while updating");
		}
		return response;
	}

	@Override
	public ComputingServicesResponse<StaffSchedule> schedule(String studentId, String date) {
		ComputingServicesResponse<StaffSchedule> response = new ComputingServicesResponse<>();
		try {
			LOGGER.info("User request received to show work schedule for " + studentId + " for the date " + date);
			response.setResponse(getAssistantServiceInstance().getSchedule(studentId, date));
			response.setStatusCode(Response.Status.OK.getStatusCode());
		} catch (Exception e) {
			LOGGER.error("Error while fetching work schedule details of " + studentId + " for date " + date, e);
			response.setStatusCode(500);
			response.setMessage("Error Occurred while fetching work schedule");
		}
		return response;
	}

	@Override
	public ComputingServicesResponse<Void> recordTimesheet(String operation, String studentId, String datetime,
			String id) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		try {
			LOGGER.info("User request received to record timesheet for " + studentId);
			getAssistantServiceInstance().recordTimesheet(operation, studentId, datetime, id);
			response.setMessage("Operation Success");
			response.setStatusCode(Response.Status.OK.getStatusCode());
		} catch (Exception e) {
			LOGGER.error("Error while saving timesheet details of " + studentId, e);
			response.setStatusCode(500);
			response.setMessage("Error Occurred while saving timesheet details");
		}
		return response;
	}

}
