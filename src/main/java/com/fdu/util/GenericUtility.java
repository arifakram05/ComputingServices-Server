package com.fdu.util;

import javax.ws.rs.core.Response.Status;

import com.fdu.model.ComputingServicesResponse;

public final class GenericUtility {

	public static ComputingServicesResponse<?> createSuccessResponse(String message) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		response.setMessage(message);
		response.setStatusCode(Status.OK.getStatusCode());
		return response;
	}

	public static ComputingServicesResponse<?> createFailureResponse(String message) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		response.setMessage(message);
		response.setStatusCode(Status.INTERNAL_SERVER_ERROR.getStatusCode());
		return response;
	}

}
