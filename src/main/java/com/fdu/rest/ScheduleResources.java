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
import com.fdu.model.LabSchedule;
import com.sun.jersey.multipart.FormDataParam;

/**
 * Consists of all end-points concerning lab schedule and lab assistant schedule
 * 
 * @author arifakrammohammed
 *
 */
@Path("/lab-schedule")
@Produces(MediaType.APPLICATION_JSON)
public class ScheduleResources {

	/**
	 * save given events in a lab's schedule
	 * 
	 * @param labschedule
	 *            schedule to save
	 * @return {@link ComputingServicesResponse} containing operation status
	 *         details
	 */
	@POST
	@Path("/save")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response saveLabSchedule(@FormDataParam("labschedule") String labschedule) {
		ComputingServicesResponse<Void> response = ScheduleOperations.getInstance().saveLabSchedule(labschedule);
		return Response.status(response.getStatusCode()).entity(response).build();
	}

	/**
	 * get all events happening in a lab
	 * 
	 * @return {@link ComputingServicesResponse} containing {@link LabSchedule}
	 */
	@GET
	@Path("/fetch")
	public Response getLabSchedule() {
		ComputingServicesResponse<LabSchedule> response = ScheduleOperations.getInstance().getLabSchedule();
		return Response.status(response.getStatusCode()).entity(response).build();
	}

	/**
	 * update an event on the lab calendar
	 * 
	 * @param labschedule
	 *            event to udpate
	 * @return {@link ComputingServicesResponse} containing operation status
	 *         details
	 */
	@POST
	@Path("/update")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response updateLabSchedule(@FormDataParam("labschedule") String labschedule) {
		ComputingServicesResponse<Void> response = ScheduleOperations.getInstance().updateLabSchedule(labschedule);
		return Response.status(response.getStatusCode()).entity(response).build();
	}

	/**
	 * update all related events
	 * 
	 * @param labschedule
	 *            event to update
	 * @return {@link ComputingServicesResponse} containing operation status
	 *         details
	 */
	@POST
	@Path("/update-all")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response updateManyEvents(@FormDataParam("labschedule") String labschedule) {
		ComputingServicesResponse<Void> response = ScheduleOperations.getInstance().updateManyEvents(labschedule);
		return Response.status(response.getStatusCode()).entity(response).build();
	}

	/**
	 * delete an event from lab calendar
	 * 
	 * @param eventId
	 *            event to delete
	 * @return {@link ComputingServicesResponse} containing operation status
	 *         details
	 */
	@DELETE
	@Path("/delete")
	public Response deleteLabSchedule(@QueryParam("eventId") String eventId) {
		ComputingServicesResponse<Void> response = ScheduleOperations.getInstance().deleteLabSchedule(eventId);
		return Response.status(response.getStatusCode()).entity(response).build();
	}

	/**
	 * delete all related events from lab calendar
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

}
