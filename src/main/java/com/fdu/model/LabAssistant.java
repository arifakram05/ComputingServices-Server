package com.fdu.model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.fdu.constants.Status;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LabAssistant {
	
	private int studentId;
	private String firstName;
	private String lastName;
	private String username;
	private String phone;
	private String address;
	private Status status;
	private String email;
	private String education;
	private String comments;
	private String specialization;
	private String profileLink;
	private byte[] resume;
	private byte[] photo;
	private Date dateApplied;
	private Date dateHired;
	private Date firstWorkingDate;
	
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
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
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

	public Date getDateHired() {
		return dateHired;
	}

	public void setDateHired(Date dateHired) {
		this.dateHired = dateHired;
	}

	public Date getFirstWorkingDate() {
		return firstWorkingDate;
	}

	public void setFirstWorkingDate(Date firstWorkingDate) {
		this.firstWorkingDate = firstWorkingDate;
	}

	public Date getDateApplied() {
		return dateApplied;
	}

	public void setDateApplied(Date dateApplied) {
		this.dateApplied = dateApplied;
	}

}
