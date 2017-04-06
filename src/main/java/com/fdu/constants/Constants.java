package com.fdu.constants;

public enum Constants {

	//object id
	OBJECTID("_id"),

	// LABASSISTANT AND JOBAPPLICANT
	FIRSTNAME("firstName"),
	LASTNAME("lastName"),
	EMAIL("email"),
	PHONE("phone"),
	DATEAPPPLIED("dateApplied"),
	DATEHIRED("dateHired"),
	EDUCATION("education"),
	RESUME("resume"),
	STUDENTID("studentId"),
	STATUS("status"),
	COMMENTS("comments"),

	// LABSCHEDULE
	LABSCHEDULEID("labScheduleId"),
	CAMPUS("campus"),
	LAB("lab"),
	STARTDATE("startDate"),
	ENDDATE("endDate"),
	STARTTIME("startTime"),
	ENDTIME("endTime"),
	ISRECURRING("isRecurring"),
	PROFESSORNAME("profName"),
	SUBJECT("subject"),

	//USERS
	USERID("userId"),
	ROLE("role"),
	PASSWORD("password"),

	//ROLES
	ROLENAME("roleName"),
	AVAILABLEPRIVS("availablePrivs"),
	ASSIGNEDPRIVS("assignedPrivs"),

	//PRIVILEGES
	NAME("name"),
	DESCRIPTION("description"),

	// names of collections
	USERS("users"),
	ROLES("roles"),
	LABSCHECULE("labschedule"),
	LABASSISTANTS("labassistants"),
	JOBAPPLICANTS("jobapplicants");

	private String value;

	private Constants(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
