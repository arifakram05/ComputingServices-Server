package com.fdu.model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Timesheet {

	boolean isClockedIn;
	Date clockedInDateTime;
	boolean isClockedOut;
	Date clockedOutDateTime;

	public boolean getIsClockedIn() {
		return isClockedIn;
	}

	public void setIsClockedIn(boolean isClockedIn) {
		this.isClockedIn = isClockedIn;
	}

	public Date getClockedInDateTime() {
		return clockedInDateTime;
	}

	public void setClockedInDateTime(Date clockedInDateTime) {
		this.clockedInDateTime = clockedInDateTime;
	}

	public boolean getIsClockedOut() {
		return isClockedOut;
	}

	public void setIsClockedOut(boolean isClockedOut) {
		this.isClockedOut = isClockedOut;
	}

	public Date getClockedOutDateTime() {
		return clockedOutDateTime;
	}

	public void setClockedOutDateTime(Date clockedOutDateTime) {
		this.clockedOutDateTime = clockedOutDateTime;
	}

}
