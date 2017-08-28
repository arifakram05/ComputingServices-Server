package com.fdu.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import com.fdu.interfaces.ScheduleOperations;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.StaffSchedule;

public class ScheduleOperationsImpl implements ScheduleOperations {

	private final static Logger LOGGER = Logger.getLogger(ManagerOperationsImpl.class);
	
	@Override
	public ComputingServicesResponse<Void> saveStaffSchedule(String staffscheduleDetails) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		List<StaffSchedule> staffschedule = null;
		try {
			LOGGER.info("Preparing to save staff schedule");
			staffschedule = new ObjectMapper().readValue(staffscheduleDetails,
					TypeFactory.collectionType(List.class, StaffSchedule.class));
			LOGGER.info("Saving staff schedule " + staffschedule.toString());
			getScheduleServiceInstance().saveStaffSchedule(staffschedule);
			response.setStatusCode(200);
			response.setMessage("Staff Schedule Saved");
			LOGGER.info("Saving staff schedule success " + staffschedule.toString());
		} catch (Exception e) {
			LOGGER.error("Error while saving staff schedule details " + staffschedule.toString(), e);
			response.setStatusCode(500);
			response.setMessage("Error occurred while saving staff schedule");
		}
		return response;
	}

	@Override
	public ComputingServicesResponse<StaffSchedule> getStaffSchedule() {
		ComputingServicesResponse<StaffSchedule> response = new ComputingServicesResponse<>();
		List<StaffSchedule> staffschedule = null;
		try {
			LOGGER.info("Preparing to fetch events hapenning in a staff");
			staffschedule = getScheduleServiceInstance().getStaffSchedule();
			LOGGER.info("Staff Schedule successfully retrieved");
			response.setStatusCode(200);
			response.setMessage("Staff Schedule");
			response.setResponse(staffschedule);
		} catch (Exception e) {
			LOGGER.error("Error while fetching staff schedule ", e);
			response.setStatusCode(500);
			response.setMessage("Error occurred. Could not get staff schedule");
		}
		return response;
	}

	@Override
	public ComputingServicesResponse<Void> updateStaffSchedule(String staffscheduleDetails) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		StaffSchedule staffschedule = null;
		try {
			LOGGER.info("Preparing to update an event on staff schedule");
			staffschedule = new ObjectMapper().readValue(staffscheduleDetails, StaffSchedule.class);
			LOGGER.info("Updating the event " + staffschedule.toString());
			getScheduleServiceInstance().updateStaffSchedule(staffschedule);
			response.setStatusCode(200);
			response.setMessage("Staff Schedule Updated");
			LOGGER.info("Updating staff schedule success " + staffschedule.toString());
		} catch (Exception e) {
			LOGGER.error("Error while updating staff schedule details " + staffschedule.toString(), e);
			response.setStatusCode(500);
			response.setMessage("Error occurred while updating staff schedule");
		}
		return response;
	}

	@Override
	public ComputingServicesResponse<Void> updateManyEvents(String staffscheduleDetails) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		StaffSchedule staffschedule = null;
		try {
			LOGGER.info("Preparing to update all related events");
			staffschedule = new ObjectMapper().readValue(staffscheduleDetails, StaffSchedule.class);
			LOGGER.info("Updating all related events " + staffschedule.toString());
			getScheduleServiceInstance().updateManyEvents(staffschedule);
			response.setStatusCode(200);
			response.setMessage("All events updated");
			LOGGER.info("Updating all events on staff calendar success " + staffschedule.toString());
		} catch (Exception e) {
			LOGGER.error("Error while updating all events on staff schedule " + staffschedule.toString(), e);
			response.setStatusCode(500);
			response.setMessage("Error occurred while updating all events on the calendar");
		}
		return response;
	}

	@Override
	public ComputingServicesResponse<Void> deleteStaffSchedule(String eventId) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		try {
			LOGGER.info("Preparing to delete an event from staff calendar");
			getScheduleServiceInstance().deleteStaffSchedule(eventId);
			LOGGER.info("Event to deleted - "+eventId);
			response.setStatusCode(200);
			response.setMessage("Deleted");			
		} catch (Exception e) {
			LOGGER.error("Error while deleting event from staff calendar "+eventId, e);
			response.setStatusCode(500);
			response.setMessage("Failed to delete event from staff calendar");
		}
		return response;
	}

	@Override
	public ComputingServicesResponse<Void> deleteManyEvents(String groupId) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		try {
			LOGGER.info("Preparing to delete multiple events from staff calendar");
			getScheduleServiceInstance().deleteManyEvents(groupId);
			LOGGER.info("Group Events to deleted - "+groupId);
			response.setStatusCode(200);
			response.setMessage("Deleted");			
		} catch (Exception e) {
			LOGGER.error("Error while deleting many events from staff calendar "+groupId, e);
			response.setStatusCode(500);
			response.setMessage("Failed to delete events from staff calendar");
		}
		return response;
	}

}
