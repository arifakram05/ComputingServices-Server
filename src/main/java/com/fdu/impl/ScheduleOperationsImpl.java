package com.fdu.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import com.fdu.interfaces.ScheduleOperations;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.LabSchedule;

public class ScheduleOperationsImpl implements ScheduleOperations {

	private final static Logger LOGGER = Logger.getLogger(ManagerOperationsImpl.class);
	
	@Override
	public ComputingServicesResponse<Void> saveLabSchedule(String labscheduleDetails) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		List<LabSchedule> labschedule = null;
		try {
			LOGGER.info("Preparing to save lab schedule");
			labschedule = new ObjectMapper().readValue(labscheduleDetails,
					TypeFactory.collectionType(List.class, LabSchedule.class));
			LOGGER.info("Saving lab schedule " + labschedule.toString());
			getScheduleServiceInstance().saveLabSchedule(labschedule);
			response.setStatusCode(200);
			response.setMessage("Lab Schedule Saved");
			LOGGER.info("Saving lab schedule success " + labschedule.toString());
		} catch (Exception e) {
			LOGGER.error("Error while saving lab schedule details " + labschedule.toString(), e);
			response.setStatusCode(500);
			response.setMessage("Error occurred while saving lab schedule");
		}
		return response;
	}

	@Override
	public ComputingServicesResponse<LabSchedule> getLabSchedule() {
		ComputingServicesResponse<LabSchedule> response = new ComputingServicesResponse<>();
		List<LabSchedule> labschedule = null;
		try {
			LOGGER.info("Preparing to fetch events hapenning in a lab");
			labschedule = getScheduleServiceInstance().getLabSchedule();
			LOGGER.info("Lab Schedule successfully retrieved");
			response.setStatusCode(200);
			response.setMessage("Lab Schedule");
			response.setResponse(labschedule);
		} catch (Exception e) {
			LOGGER.error("Error while fetching lab schedule ", e);
			response.setStatusCode(500);
			response.setMessage("Error occurred. Could not get lab schedule");
		}
		return response;
	}

	@Override
	public ComputingServicesResponse<Void> updateLabSchedule(String labscheduleDetails) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		LabSchedule labschedule = null;
		try {
			LOGGER.info("Preparing to update an event on lab schedule");
			labschedule = new ObjectMapper().readValue(labscheduleDetails, LabSchedule.class);
			LOGGER.info("Updating the event " + labschedule.toString());
			getScheduleServiceInstance().updateLabSchedule(labschedule);
			response.setStatusCode(200);
			response.setMessage("Lab Schedule Updated");
			LOGGER.info("Updating lab schedule success " + labschedule.toString());
		} catch (Exception e) {
			LOGGER.error("Error while updating lab schedule details " + labschedule.toString(), e);
			response.setStatusCode(500);
			response.setMessage("Error occurred while updating lab schedule");
		}
		return response;
	}

}
