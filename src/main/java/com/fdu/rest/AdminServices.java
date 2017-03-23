package com.fdu.rest;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fdu.interfaces.Operations;
import com.fdu.model.LabSchedule;
import com.fdu.response.ComputingServicesResponse;

@Path("/admin")
public class AdminServices {

	@POST
	@Path("/saveLabSchedule")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response hireJobApplicant(String labScheduleJSON)
			throws JsonParseException, JsonMappingException, IOException {

		LabSchedule labSchedule = new ObjectMapper().readValue(labScheduleJSON, LabSchedule.class);
		ComputingServicesResponse response = Operations.getInstance().saveLabSchedule(labSchedule);
		return Response.ok(response).build();
	}
}
