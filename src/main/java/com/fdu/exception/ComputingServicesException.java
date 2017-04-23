package com.fdu.exception;

/**
 * Custom exception class
 * 
 * @author arifakrammohammed
 *
 */
public class ComputingServicesException extends Exception {

	private static final long serialVersionUID = 1L;

	public ComputingServicesException() {
		super();
	}

	public ComputingServicesException(String message) {
		super(message);
	}

	public ComputingServicesException(Exception e) {
		super(e);
	}

}
