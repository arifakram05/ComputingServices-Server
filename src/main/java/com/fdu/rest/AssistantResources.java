package com.fdu.rest;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.fdu.interfaces.AssistantOperations;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.Shift;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/assistant")
public class AssistantResources {

	@POST
	@Path("/updateProfile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ComputingServicesResponse<Void> updateLAProfile(@FormDataParam("labassistant") String labassistant,
			@FormDataParam("resume") InputStream resume,
			@FormDataParam("resume") FormDataContentDisposition resumeDetail, @FormDataParam("photo") InputStream photo,
			@FormDataParam("photo") FormDataContentDisposition photoDetail) {
		ComputingServicesResponse<Void> response = AssistantOperations.getInstance().updateProfile(labassistant, resume,
				photo);
		return response;
	}

	/**
	 * Show a Lab Assistant's schedule for the given dates.
	 * 
	 * @param request
	 *            contains a start and end date.
	 * @return {@link ComputingServicesResponse} containing all the assigned
	 *         shifts between two given dates
	 */
	@POST
	@Path("/schedule")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ComputingServicesResponse<Shift> schedule(@FormDataParam("request") String request) {
		return AssistantOperations.getInstance().schedule(request);
	}

}
