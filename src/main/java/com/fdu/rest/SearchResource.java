package com.fdu.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fdu.controller.interfaces.SearchController;
import com.fdu.model.User;

@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {

	@GET
	@Path("/users")
	public Response searchUsers(@QueryParam("user") String searchText) {
		try {
			List<User> users = SearchController.Factory.getInstance().searchUsers(searchText);
			return Response.status(Status.OK).entity(users).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(null).build();
		}
	}

	@GET
	@Path("/labassistants")
	public Response searchLabAssistants(@QueryParam("labAssistant") String searchText) {
		try {
			List<User> labAssistants = SearchController.Factory.getInstance().searchLabAssistants(searchText);
			return Response.status(Status.OK).entity(labAssistants).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(null).build();
		}
	}
}
