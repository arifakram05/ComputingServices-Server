package com.fdu.constants;

public enum Constants {

	//object id
	OBJECTID("_id"),
	$OBJECTID("$oid"),
	//file name
	FILENAME("filename"),

	// LABASSISTANT AND JOBAPPLICANT
	FIRSTNAME("firstName"),
	LASTNAME("lastName"),
	EMAIL("email"),
	PHONE("phone"),
	DATEAPPPLIED("dateApplied"),
	DATEHIRED("dateHired"),
	EDUCATION("education"),
	SPECIALIZATION("specialization"),
	PROFILELINK("profileLink"),
	RESUME("resume"),
	RESUME_EXTN("resumeExtn"),
	PHOTO("photo"),
	PHOTO_EXTN("photoExtn"),
	STUDENTID("studentId"),
	STATUS("status"),
	COMMENTS("comments"),

	// JOB STATUSES
	SUBMITTED("SUBMITTED"),

	// STAFFSCHEDULE
	GROUPID("groupId"),
	LABNAME("labName"),
	START("start"),
	END("end"),
	ALLDAY("allDay"),
	TITLE("title"),
	BGCOLOR("backgroundColor"),
	DATE("date"),
	TIMESHEET("timesheet"),

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
	
	// SHIFT
	ISCLOCKEDIN("isClockedIn"),
	ISCLOCKEDOUT("isClockedOut"),
	CLOCKEDINDATETIME("clockedInDateTime"),
	CLOCKEDOUTDATETIME("clockedOutDateTime"),

	// names of collections
	USERS("users"),
	ROLES("roles"),
	STAFFSCHECULE("staffschedule"),
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
