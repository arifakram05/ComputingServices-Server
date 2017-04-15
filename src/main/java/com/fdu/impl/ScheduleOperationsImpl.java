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
			LOGGER.info("Saving lab schedule success " + labscheduleDetails.toString());
		} catch (Exception e) {
			LOGGER.error("Error while saving lab schedule details " + labschedule.toString(), e);
			response.setStatusCode(500);
			response.setMessage("Error occurred while saving lab schedule");
		}
		return response;
	}

}
