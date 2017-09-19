package com.fdu.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fdu.interfaces.ScheduleOperations;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.StaffSchedule;
import com.sun.jersey.multipart.FormDataParam;

/**
 * Consists of all end-points concerning staff schedule and staff assistant
 * schedule
 * 
 * @author arifakrammohammed
 *
 */
@Path("/staff-schedule")
@Produces(MediaType.APPLICATION_JSON)
public class ScheduleResources {

	/**
	 * save given events in a staff's schedule
	 * 
	 * @param staffschedule
	 *            schedule to save
	 * @return {@link ComputingServicesResponse} containing operation status
	 *         details
	 */
	@POST
	@Path("/save")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response saveStaffSchedule(@FormDataParam("staffschedule") String staffschedule) {
		ComputingServicesResponse<Void> response = ScheduleOperations.getInstance().saveStaffSchedule(staffschedule);
		return Response.status(response.getStatusCode()).entity(response).build();
	}

	/**
	 * get all events happening in a staff
	 * 
	 * @return {@link ComputingServicesResponse} containing
	 *         {@link StaffSchedule}
	 */
	@GET
	@Path("/fetch")
	public Response getStaffSchedule() {
		ComputingServicesResponse<StaffSchedule> response = ScheduleOperations.getInstance().getStaffSchedule();
		return Response.status(response.getStatusCode()).entity(response).build();
	}

	/**
	 * update an event on the staff calendar
	 * 
	 * @param staffschedule
	 *            event to udpate
	 * @return {@link ComputingServicesResponse} containing operation status
	 *         details
	 */
	@POST
	@Path("/update")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response updateStaffSchedule(@FormDataParam("staffschedule") String staffschedule) {
		ComputingServicesResponse<Void> response = ScheduleOperations.getInstance().updateStaffSchedule(staffschedule);
		return Response.status(response.getStatusCode()).entity(response).build();
	}

	/**
	 * update all related events
	 * 
	 * @param staffschedule
	 *            event to update
	 * @return {@link ComputingServicesResponse} containing operation status
	 *         details
	 */
	@POST
	@Path("/update-all")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response updateManyEvents(@FormDataParam("staffschedule") String staffschedule) {
		ComputingServicesResponse<Void> response = ScheduleOperations.getInstance().updateManyEvents(staffschedule);
		return Response.status(response.getStatusCode()).entity(response).build();
	}

	/**
	 * delete an event from staff calendar
	 * 
	 * @param eventId
	 *            event to delete
	 * @return {@link ComputingServicesResponse} containing operation status
	 *         details
	 */
	@DELETE
	@Path("/delete")
	public Response deleteStaffSchedule(@QueryParam("eventId") String eventId) {
		ComputingServicesResponse<Void> response = ScheduleOperations.getInstance().deleteStaffSchedule(eventId);
		return Response.status(response.getStatusCode()).entity(response).build();
	}

	/**
	 * delete all related events from staff calendar
	 * 
	 * @param groupId
	 *            group of events to delete
	 * @return {@link ComputingServicesResponse} containing operation status
	 *         details
	 */
	@DELETE
	@Path("/delete-all")
	public Response deleteManyEvents(@QueryParam("groupId") String groupId) {
		ComputingServicesResponse<Void> response = ScheduleOperations.getInstance().deleteManyEvents(groupId);
		return Response.status(response.getStatusCode()).entity(response).build();
	}

	/**
	 * approve an event on the staff calendar
	 * 
	 * @param staffschedule
	 *            event to update
	 * @return {@link ComputingServicesResponse} containing operation status
	 *         details
	 */
	@POST
	@Path("/approve")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response approveStaffSchedule(@FormDataParam("staffschedule") String staffschedule) {
		ComputingServicesResponse<Void> response = ScheduleOperations.getInstance().approveStaffSchedule(staffschedule);
		return Response.status(response.getStatusCode()).entity(response).build();
	}

	/**
	 * approve all related events
	 * 
	 * @param staffschedule
	 *            event to udpate
	 * @return {@link ComputingServicesResponse} containing operation status
	 *         details
	 */
	@POST
	@Path("/approve-all")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response approveManyEvents(@FormDataParam("staffschedule") String staffschedule) {
		ComputingServicesResponse<Void> response = ScheduleOperations.getInstance().approveManyEvents(staffschedule);
		return Response.status(response.getStatusCode()).entity(response).build();
	}

}
