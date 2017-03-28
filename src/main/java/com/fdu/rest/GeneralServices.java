package com.fdu.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fdu.model.ComputingServicesResponse;
import com.sun.jersey.multipart.FormDataParam;

@Path("/general")
public class GeneralServices {

	/**
	 * Validate user credentials and log-in
	 * 
	 * @param userDetails
	 *            associate to log-in
	 * @return {@link ComputingServicesResponse} containing {@link User} details
	 */
	@POST
	@Path("/login")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public ComputingServicesResponse<Void> login(@FormDataParam("userDetails") String userDetails) {

		/*ScrumBoardResponse<Associate> response = ScrumBoard.getInstance().login(associateDetails);
		return response;*/
		return null;
	}
}
	