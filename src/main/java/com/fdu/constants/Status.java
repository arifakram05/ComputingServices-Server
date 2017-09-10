package com.fdu.constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

public enum Status {

	NEW("NEW"), ACTIVE("ACTIVE"), INACTIVE("INACTIVE"), ALUMNI("ALUMNI");

	private String value;

	private Status(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	private static Map<String, Status> namesMap = new HashMap<String, Status>(3);

	static {
		namesMap.put("NEW", NEW);
		namesMap.put("ACTIVE", ACTIVE);
		namesMap.put("INACTIVE", INACTIVE);
		namesMap.put("ALUMNI", ALUMNI);
	}

	/**
	 * Will be invoked during object creation from JSON<br/>
	 * i.e. during serialization<br/>
	 * Given an enum value, returns the enum constant
	 * 
	 * @param value
	 *            value of the enum constant
	 * @return {@link Status}
	 */
	@JsonCreator
	public static Status forValue(String value) {
		return namesMap.get(value);
	}

	/**
	 * Will be invoked while creating a JSON string from Object<br/>
	 * i.e. during de-serialization
	 * 
	 * @return enum Constant's value
	 */
	@JsonValue
	public String toValue() {
		for (Entry<String, Status> entry : namesMap.entrySet()) {
			if (entry.getValue() == this)
				return entry.getKey();
		}

		return null;
	}
}
