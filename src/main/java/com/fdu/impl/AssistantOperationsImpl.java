package com.fdu.impl;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.fdu.interfaces.AssistantOperations;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.LabAssistant;

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
			response = new ComputingServicesResponse<>();
			response.setStatusCode(500);
			response.setMessage("Error Occurred while updating");
		}
		return response;
	}

}
