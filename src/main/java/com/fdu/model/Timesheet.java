package com.fdu.model;

import java.time.LocalDateTime;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Timesheet {

	boolean isClockedIn;
	LocalDateTime clockedInDateTime;
	boolean isClockedOut;
	LocalDateTime clockedOutDateTime;

	public boolean isClockedIn() {
		return isClockedIn;
	}

	public void setClockedIn(boolean isClockedIn) {
		this.isClockedIn = isClockedIn;
	}

	public LocalDateTime getClockedInDateTime() {
		return clockedInDateTime;
	}

	public void setClockedInDateTime(LocalDateTime clockedInDateTime) {
		this.clockedInDateTime = clockedInDateTime;
	}

	public boolean isClockedOut() {
		return isClockedOut;
	}

	public void setClockedOut(boolean isClockedOut) {
		this.isClockedOut = isClockedOut;
	}

	public LocalDateTime getClockedOutDateTime() {
		return clockedOutDateTime;
	}

	public void setClockedOutDateTime(LocalDateTime clockedOutDateTime) {
		this.clockedOutDateTime = clockedOutDateTime;
	}

}
