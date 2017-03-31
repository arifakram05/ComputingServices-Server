package com.fdu.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fdu.interfaces.ManagerOperations;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.JobApplicant;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
public class ManagerServices {

	/*@POST
	@Path("/saveLabSchedule")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response hireJobApplicant(String labScheduleJSON)
			throws JsonParseException, JsonMappingException, IOException {

		LabSchedule labSchedule = new ObjectMapper().readValue(labScheduleJSON, LabSchedule.class);
		ComputingServicesResponse response = Operations.getInstance().saveLabSchedule(labSchedule);
		return Response.ok(response).build();
	}*/

	/**
	 * View all job applicants
	 * 
	 * @return {@link ComputingServicesResponse} containing response details
	 */
	@GET
	@Path("/viewJobApplicants")
	public Response viewJobApplicants() {
		ComputingServicesResponse<JobApplicant> jobApplicants = ManagerOperations.getInstance().viewJobApplicants();
		return Response.status(jobApplicants.getStatusCode()).entity(jobApplicants).build();
	}
}
