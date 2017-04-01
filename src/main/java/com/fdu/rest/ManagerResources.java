package com.fdu.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fdu.interfaces.ManagerOperations;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.JobApplicant;
import com.fdu.model.LabAssistant;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
public class ManagerResources {

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

	/**
	 * View all lab assistants
	 * 
	 * @return {@link ComputingServicesResponse} containing response details
	 */
	@GET
	@Path("/viewLabAssistants")
	public Response viewLabAssistants() {
		ComputingServicesResponse<LabAssistant> labAssistants = ManagerOperations.getInstance().viewLabAssistants();
		return Response.status(labAssistants.getStatusCode()).entity(labAssistants).build();
	}

	@DELETE
	@Path("/deleteJobApplicant")
	public Response deleteJobApplicant(@QueryParam("studentId") int studentId) {
		ComputingServicesResponse<Void> response = ManagerOperations.getInstance().deleteJobApplicant(studentId);
		return Response.status(response.getStatusCode()).entity(response).build();
	}

}
