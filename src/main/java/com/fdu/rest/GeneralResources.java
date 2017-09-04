package com.fdu.rest;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fdu.interfaces.GeneralOperations;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.User;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/general")
@Produces(MediaType.APPLICATION_JSON)
public class GeneralResources {

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
	public Response login(@FormDataParam("userDetails") String userDetails) {
		ComputingServicesResponse<User> response = GeneralOperations.getInstance().login(userDetails);
		return Response.status(response.getStatusCode()).entity(response).build();
	}

	@POST
	@Path("/register")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ComputingServicesResponse<Void> register(@FormDataParam("userDetails") String userDetails) {
		ComputingServicesResponse<Void> response = GeneralOperations.getInstance().register(userDetails);
		return response;
	}

	@GET
	@Path("/check")
	public ComputingServicesResponse<Void> canUserRegister(@QueryParam("userId") String userId) {
		ComputingServicesResponse<Void> response = GeneralOperations.getInstance().canUserRegister(userId);
		return response;
	}

	@GET
	@Path("/search")
	public ComputingServicesResponse<User> searchUsers(@QueryParam("user") String searchText) {
		ComputingServicesResponse<User> response = GeneralOperations.getInstance().searchUsers(searchText);
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
