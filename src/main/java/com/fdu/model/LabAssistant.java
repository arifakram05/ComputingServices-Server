package com.fdu.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.fdu.constants.Status;

@JsonIgnoreProperties(value = { "resume", "photo" }, ignoreUnknown = true)
public class LabAssistant {
	
	private int studentId;
	private String firstName;
	private String lastName;
	private String phone;
	private Status status;
	private String email;
	private String education;
	private String comments;
	private String specialization;
	private String profileLink;
	private byte[] resume;
	private byte[] photo;
	private String dateApplied;
	private String dateHired;
	private String firstWorkingDate;
	
	public int getStudentId() {
		return studentId;
	}
	
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEducation() {
		return education;
	}
	
	public void setEducation(String education) {
		this.education = education;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getSpecialization() {
		return specialization;
	}
	
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	public String getProfileLink() {
		return profileLink;
	}
	
	public void setProfileLink(String profileLink) {
		this.profileLink = profileLink;
	}
	
	public byte[] getResume() {
		return resume;
	}
	
	public void setResume(byte[] resume) {
		this.resume = resume;
	}
	
	public byte[] getPhoto() {
		return photo;
	}
	
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getDateHired() {
		return dateHired;
	}

	public void setDateHired(String dateHired) {
		this.dateHired = dateHired;
	}

	public String getFirstWorkingDate() {
		return firstWorkingDate;
	}

	public void setFirstWorkingDate(String firstWorkingDate) {
		this.firstWorkingDate = firstWorkingDate;
	}

	public String getDateApplied() {
		return dateApplied;
	}

	public void setDateApplied(String dateApplied) {
		this.dateApplied = dateApplied;
	}

}
