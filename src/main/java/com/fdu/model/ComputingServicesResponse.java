package com.fdu.model;

import java.util.List;

/**
 * Each user request is served with the object of this class
 * 
 * @author arifakrammohammed
 *
 * @param <T>
 */
public class ComputingServicesResponse<T> {

	private int statusCode;
	private String message;
	private List<T> response;
	private String authToken;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getResponse() {
		return response;
	}

	public void setResponse(List<T> response) {
		this.response = response;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

}
