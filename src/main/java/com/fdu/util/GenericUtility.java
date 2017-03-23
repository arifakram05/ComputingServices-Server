package com.fdu.util;

import com.fdu.response.ComputingServicesResponse;

public class GenericUtility {

	/**
	 * Constructs an object with given details. This object is then sent to the UI for display to the user.
	 * @param message to be shown to the user
	 * @param statusCode number indications operation status.<br/>1 = Success<br/>2 = Partial Success<br/>3 = Error
	 * @return
	 */
	public static ComputingServicesResponse response(String message, int statusCode) {
		ComputingServicesResponse response = new ComputingServicesResponse();
		response.setMessage(message);
		response.setStatusCode(statusCode);
		return response;
	} 
}
