package com.fdu.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fdu.constants.ControllerType;
import com.fdu.controller.factory.FactoryCreator;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.User;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	@GET
	@Path("users")
	public Response getAllUsers() {
		ComputingServicesResponse<User> response = null;
		try {
			response = FactoryCreator.getFactory(ControllerType.USERS).getUser().get();
			return Response.status(Status.OK).entity(response).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}

	@PUT
	@Path("block/{id}")
	public Response blockUser(@PathParam("id") String id) {
		ComputingServicesResponse<Void> response = null;
		try {
			response = FactoryCreator.getFactory(ControllerType.USERS).getUser().blockUser(id);
			return Response.status(Status.OK).entity(response).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}

	@PUT
	@Path("unblock/{id}")
	public Response unblockUser(@PathParam("id") String id) {
		ComputingServicesResponse<Void> response = null;
		try {
			response = FactoryCreator.getFactory(ControllerType.USERS).getUser().unblockUser(id);
			return Response.status(Status.OK).entity(response).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}

	@DELETE
	@Path("delete/{id}")
	public Response deleteUser(@PathParam("id") String id) {
		ComputingServicesResponse<Void> response = null;
		try {
			response = FactoryCreator.getFactory(ControllerType.USERS).getUser().deleteUser(id);
			return Response.status(Status.OK).entity(response).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}

	@POST
	@Path("/users")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response hireJobApplicant(User user) {
		ComputingServicesResponse<Void> response = null;
		try {
			response = FactoryCreator.getFactory(ControllerType.USERS).getUser().addUser(user);
			return Response.status(Status.OK).entity(response).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}

}
