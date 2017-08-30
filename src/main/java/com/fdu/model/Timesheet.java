package com.fdu.model;

import java.time.LocalDateTime;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Timesheet {

	boolean isClockedIn;
	LocalDateTime clockedInDateTime;
	boolean isClockedOut;
	LocalDateTime clockedOutDateTime;

	public boolean getIsClockedIn() {
		return isClockedIn;
	}

	public void setIsClockedIn(boolean isClockedIn) {
		this.isClockedIn = isClockedIn;
	}

	public LocalDateTime getClockedInDateTime() {
		return clockedInDateTime;
	}

	public void setClockedInDateTime(LocalDateTime clockedInDateTime) {
		this.clockedInDateTime = clockedInDateTime;
	}

	public boolean getIsClockedOut() {
		return isClockedOut;
	}

	public void setIsClockedOut(boolean isClockedOut) {
		this.isClockedOut = isClockedOut;
	}

	public LocalDateTime getClockedOutDateTime() {
		return clockedOutDateTime;
	}

	public void setClockedOutDateTime(LocalDateTime clockedOutDateTime) {
		this.clockedOutDateTime = clockedOutDateTime;
	}

}
