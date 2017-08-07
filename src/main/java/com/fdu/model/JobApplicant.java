package com.fdu.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Hold's Job Applicant's information
 * 
 * @author arifakrammohammed
 *
 */
@JsonIgnoreProperties(value = { "resume" }, ignoreUnknown = true)
public class JobApplicant {

	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String education;
	private byte[] resume;
	private String resumeExtn;
	private String studentId;
	private String dateApplied;
	private String status;

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

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getDateApplied() {
		return dateApplied;
	}

	public void setDateApplied(String dateApplied) {
		this.dateApplied = dateApplied;
	}

	public String getResumeExtn() {
		return resumeExtn;
	}

	public void setResumeExtn(String resumeExtn) {
		this.resumeExtn = resumeExtn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
