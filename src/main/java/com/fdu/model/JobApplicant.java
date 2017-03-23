package com.fdu.model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * POJO to fill in with job applicant's information
 * @author arifakrammohammed
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobApplicant {

	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String education;
	private byte[] resume;
	private int studentId;
	private Date dateApplied;
	
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEducation() {
		return education;
	}
	
	public void setEducation(String education) {
		this.education = education;
	}

	public byte[] getResume() {
		return resume;
	}

	public void setResume(byte[] resume) {
		this.resume = resume;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public Date getDateApplied() {
		return dateApplied;
	}

	public void setDateApplied(Date dateApplied) {
		this.dateApplied = dateApplied;
	}
	
}
