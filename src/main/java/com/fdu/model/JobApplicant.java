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
	private int studentId;
	private String dateApplied;

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

}
