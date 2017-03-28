package com.fdu.constants;

public enum EducationLevel {

	ASSOCIATES("ASSOCIATES"), UNDERGRAD("UNDERGRAD"), GRAD("GRAD"), POSTGRAD("POSTGRAD");

	private String value;

	private EducationLevel(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static String getName(String value) {
		for (EducationLevel e : EducationLevel.values()) {
			if (value.equals(e.value)) {
				return e.name();
			}
		}
		return null;
	}

}
