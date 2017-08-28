package com.fdu.model;

public class Shift {

	String labName;
	String startTime;
	String endTime;
	String startDate;
	String endDate;
	boolean isClockedIn;
	boolean isClockedOut;

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean isClockedIn() {
		return isClockedIn;
	}

	public void setClockedIn(boolean isClockedIn) {
		this.isClockedIn = isClockedIn;
	}

	public boolean isClockedOut() {
		return isClockedOut;
	}

	public void setClockedOut(boolean isClockedOut) {
		this.isClockedOut = isClockedOut;
	}

}
