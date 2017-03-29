package com.fdu.rest;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fdu.interfaces.GeneralOperations;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.User;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/general")
@Produces(MediaType.APPLICATION_JSON)
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
	public ComputingServicesResponse<User> login(@FormDataParam("userDetails") String userDetails) {
		ComputingServicesResponse<User> response = GeneralOperations.getInstance().login(userDetails);
		return response;
	}

	@POST
	@Path("/register")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ComputingServicesResponse<Void> register(@FormDataParam("userDetails") String userDetails) {
		ComputingServicesResponse<Void> response = GeneralOperations.getInstance().register(userDetails);
		return response;
	}

	@POST
	@Path("/careers")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ComputingServicesResponse<Void> careers(@FormDataParam("application") String application,
			@FormDataParam("resume") InputStream resume, @FormDataParam("resume") FormDataContentDisposition fileDetail) {

		ComputingServicesResponse<Void> response = GeneralOperations.getInstance().careers(application, resume);
		return response;
	}
}
