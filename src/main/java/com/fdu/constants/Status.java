package com.fdu.constants;

public enum Status {

	ACTIVE("ACTIVE"),
	INACTIVE("INACTIVE"),
	ALUMNI("ALUMNI");
	
	private String value;
	
	private Status(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
