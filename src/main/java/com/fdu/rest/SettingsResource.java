package com.fdu.rest;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fdu.controller.interfaces.SettingsController;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.util.GenericUtility;
import com.sun.jersey.multipart.FormDataParam;

@Path("/settings")
@Produces(MediaType.APPLICATION_JSON)
public class SettingsResource {

	@GET
	public Response getSettings() {
		try {
			Map<String, String> response = SettingsController.Factory.getInstance().getSettings();
			return Response.status(Status.OK).entity(response).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(null).build();
		}
	}

	@PUT
	@Path("/email/{email}")
	public Response configureEmail(@PathParam("email") String email) {
		ComputingServicesResponse<Void> response = null;
		try {
			response = SettingsController.Factory.getInstance().configureEmail(email);
			return Response.status(Status.OK).entity(response).build();
		} catch (Exception e) {
			response = GenericUtility.createFailureResponse(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}

	@PUT
	@Path("/password")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response changePassword(@FormDataParam("passwordDetails") String passwordDetails) {
		ComputingServicesResponse<Void> response = null;
		try {
			response = SettingsController.Factory.getInstance().changePassword(passwordDetails);
			return Response.status(Status.OK).entity(response).build();
		} catch (Exception e) {
			response = GenericUtility.createFailureResponse(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}

	@PUT
	@Path("/password/reset/{userId}")
	public Response resetPassword(@PathParam("userId") String userId) {
		ComputingServicesResponse<Void> response = null;
		try {
			response = SettingsController.Factory.getInstance().resetPassword(userId);
			return Response.status(Status.OK).entity(response).build();
		} catch (Exception e) {
			response = GenericUtility.createFailureResponse(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}

	@PUT
	@Path("/subnet-range")
	public Response defineSubnetRange(@QueryParam("start") String start, @QueryParam("end") String end) {
		ComputingServicesResponse<Void> response = null;
		try {
			response = SettingsController.Factory.getInstance().defineSubnetRange(start, end);
			return Response.status(Status.OK).entity(response).build();
		} catch (Exception e) {
			response = GenericUtility.createFailureResponse(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}
}
