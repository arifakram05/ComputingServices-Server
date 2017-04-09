package com.fdu.rest;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.fdu.interfaces.AssistantOperations;
import com.fdu.model.ComputingServicesResponse;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/assistant")
public class AssistantResources {

	@POST
	@Path("/updateProfile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ComputingServicesResponse<Void> updateLAProfile(@FormDataParam("labassistant") String labassistant,
			@FormDataParam("resume") InputStream resume,
			@FormDataParam("resume") FormDataContentDisposition resumeDetail, @FormDataParam("photo") InputStream photo,
			@FormDataParam("photo") FormDataContentDisposition photoDetail) {
		ComputingServicesResponse<Void> response = AssistantOperations.getInstance().updateProfile(labassistant, resume, photo);
		return response;
	}

}
