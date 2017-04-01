package com.fdu.rest;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fdu.model.JobApplicant;
import com.sun.jersey.multipart.FormDataParam;

@Path("/services")
public class AssistantResources {

	/**
	 * Remove multiple job applicant from the race
	 * @param jobApplicants
	 * @return
	 */
	@POST
	@Path("/deleteJobApplicants")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteJobApplicants(List<JobApplicant> jobApplicants) {
		return Response.ok(200).build();
	}

	/**
	 * Update job applicant details
	 * @param formData
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@POST
	@Path("/updateJobApplicant")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response updateJobApplicant(@FormDataParam("model") String formData)
			throws JsonParseException, JsonMappingException, IOException {

		JobApplicant jobApplicantDetails = new ObjectMapper().readValue(formData, JobApplicant.class);
		return Response.ok(200).build();
	}
	
	/**
	 * Email job applicant
	 * @param jobApplicantDetailsJSON
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@POST
	@Path("/emailJobApplicant")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response emailJobApplicant(String jobApplicantDetailsJSON)
			throws JsonParseException, JsonMappingException, IOException {

		JobApplicant jobApplicantDetails = new ObjectMapper().readValue(jobApplicantDetailsJSON, JobApplicant.class);
		return Response.ok(200).build();
	}

}
