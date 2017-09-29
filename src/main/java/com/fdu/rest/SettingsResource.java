package com.fdu.rest;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fdu.controller.interfaces.SettingsController;
import com.fdu.model.ComputingServicesResponse;

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
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}
}
