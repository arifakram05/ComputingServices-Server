package com.fdu.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fdu.interfaces.ManagerOperations;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.JobApplicant;
import com.fdu.model.LabAssistant;
import com.fdu.model.Role;
import com.sun.jersey.multipart.FormDataParam;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
public class ManagerResources {

	/*
	 * Below are services related to Job Applicant
	 */

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

	@DELETE
	@Path("/deleteJobApplicant")
	public Response deleteJobApplicant(@QueryParam("studentId") int studentId) {
		ComputingServicesResponse<Void> response = ManagerOperations.getInstance().deleteJobApplicant(studentId);
		return Response.status(response.getStatusCode()).entity(response).build();
	}

	@POST
	@Path("/hireJobApplicant")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response hireJobApplicant(@FormDataParam("labAssistant") String labAssistant) {
		ComputingServicesResponse<Void> response = ManagerOperations.getInstance().hireJobApplicant(labAssistant);
		return Response.status(response.getStatusCode()).entity(response).build();
	}

	/*
	 * Below are services related to Lab Assistant
	 */

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
	@Path("/deleteLabAssistant")
	public Response deleteLabAssistant(@QueryParam("studentId") int studentId) {
		ComputingServicesResponse<Void> response = ManagerOperations.getInstance().deleteLabAssistant(studentId);
		return Response.status(response.getStatusCode()).entity(response).build();
	}

	@POST
	@Path("/updateLabAssistant")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response updateLabAssistant(@FormDataParam("labAssistant") String labAssistant) {
		ComputingServicesResponse<Void> response = ManagerOperations.getInstance().updateLabAssistant(labAssistant);
		return Response.status(response.getStatusCode()).entity(response).build();
	}

	/**
	 * Authorize a user such that he/she can register and start using this
	 * system
	 * 
	 * @param user
	 *            user who needs to be registered
	 * @return {@link ComputingServicesResponse} containing response details
	 */
	@POST
	@Path("/authorize")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response authorizeUser(@FormDataParam("user") String user) {
		ComputingServicesResponse<Void> response = ManagerOperations.getInstance().authorizeUser(user);
		return Response.status(response.getStatusCode()).entity(response).build();
	}

	@GET
	@Path("/roles")
	public Response getRoles() {
		ComputingServicesResponse<Role> response = ManagerOperations.getInstance().getRoles();
		return Response.status(response.getStatusCode()).entity(response).build();
	}

	@POST
	@Path("/updateRole")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response updateRole(@FormDataParam("role") String role) {
		ComputingServicesResponse<Void> response = ManagerOperations.getInstance().updateRole(role);
		return Response.status(response.getStatusCode()).entity(response).build();
	}

}
