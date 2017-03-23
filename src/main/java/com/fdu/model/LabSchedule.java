package com.fdu.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LabSchedule {
	
	private int labScheduleId;
	private byte campus;
	private byte lab;
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;
	private boolean isRecurring;
	private String profName;
	private String subject;
	
	public int getLabScheduleId() {
		return labScheduleId;
	}
	
	public void setLabScheduleId(int labScheduleId) {
		this.labScheduleId = labScheduleId;
	}
	
	public byte getCampus() {
		return campus;
	}
	
	public void setCampus(byte campus) {
		this.campus = campus;
	}
	
	public byte getLab() {
		return lab;
	}
	
	public void setLab(byte lab) {
		this.lab = lab;
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
	
	public boolean isRecurring() {
		return isRecurring;
	}
	
	public void setRecurring(boolean isRecurring) {
		this.isRecurring = isRecurring;
	}
	
	public String getProfName() {
		return profName;
	}
	
	public void setProfName(String profName) {
		this.profName = profName;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
}
