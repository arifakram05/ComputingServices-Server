package com.fdu.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
	 * get all events hapenning in a lab
	 * 
	 * @return {@link ComputingServicesResponse} containing {@link LabSchedule}
	 */
	@GET
	@Path("/fetch")
	public Response getLabSchedule() {
		ComputingServicesResponse<LabSchedule> response = ScheduleOperations.getInstance().getLabSchedule();
		return Response.status(response.getStatusCode()).entity(response).build();
	}

}