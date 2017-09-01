package com.fdu.rest;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.fdu.interfaces.AssistantOperations;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.StaffSchedule;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/assistant")
public class AssistantResources {

	@POST
	@Path("/update-profile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ComputingServicesResponse<Void> updateProfile(@FormDataParam("labassistant") String labassistant,
			@FormDataParam("resume") InputStream resume,
			@FormDataParam("resume") FormDataContentDisposition resumeDetail, @FormDataParam("photo") InputStream photo,
			@FormDataParam("photo") FormDataContentDisposition photoDetail) {
		ComputingServicesResponse<Void> response = AssistantOperations.getInstance().updateProfile(labassistant, resume,
				photo);
		return response;
	}

	/**
	 * Show a Lab Assistant's schedule for the given date.
	 * 
	 * @param studentId
	 *            Lab Assistant Id
	 * @param date
	 *            date whose work schedule is to be retrieved
	 * @return {@link ComputingServicesResponse} containing all the assigned
	 *         shifts for the given date
	 */
	@GET
	@Path("/single-schedule")
	@Produces(MediaType.APPLICATION_JSON)
	public ComputingServicesResponse<StaffSchedule> getShiftSchedule(@QueryParam("studentId") String studentId,
			@QueryParam("date") String date) {
		return AssistantOperations.getInstance().getShiftSchedule(studentId, date);
	}

	@POST
	@Path("/record-work")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public ComputingServicesResponse<Void> saveTimesheet(@FormDataParam("operation") String operation,
			@FormDataParam("studentId") String studentId, @FormDataParam("datetime") String datetime,
			@FormDataParam("id") String id) {
		return AssistantOperations.getInstance().saveTimesheet(operation, studentId, datetime, id);
	}

	/**
	 * Show a Lab Assistant's schedule for the given two dates.
	 * 
	 * @param studentId
	 *            Lab Assistant Id
	 * @param startDate
	 *            start date
	 * @param endDate
	 *            end date
	 * @return {@link ComputingServicesResponse} containing all the assigned
	 *         shifts for the given two dates
	 */
	@GET
	@Path("/many-schedules")
	@Produces(MediaType.APPLICATION_JSON)
	public ComputingServicesResponse<StaffSchedule> getShiftSchedule(@QueryParam("studentId") String studentId,
			@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
		return AssistantOperations.getInstance().getShiftSchedule(studentId, startDate, endDate);
	}

}
