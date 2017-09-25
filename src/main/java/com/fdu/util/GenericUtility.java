package com.fdu.util;

import java.util.List;

import javax.ws.rs.core.Response.Status;

import com.fdu.model.ComputingServicesResponse;

public final class GenericUtility {

	public static ComputingServicesResponse<Void> createSuccessResponse(String message) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		response.setMessage(message);
		response.setStatusCode(Status.OK.getStatusCode());
		return response;
	}

	public static <T> ComputingServicesResponse<T> createSuccessResponse(String message, List<T> data) {
		ComputingServicesResponse<T> computingServicesResponse = new ComputingServicesResponse<>();
		computingServicesResponse.setMessage(message);
		computingServicesResponse.setResponse(data);
		computingServicesResponse.setStatusCode(Status.OK.getStatusCode());
		return computingServicesResponse;
	}

	public static ComputingServicesResponse<Void> createFailureResponse(String message) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		response.setMessage(message);
		response.setStatusCode(Status.INTERNAL_SERVER_ERROR.getStatusCode());
		return response;
	}

}
