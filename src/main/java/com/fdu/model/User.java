package com.fdu.model;

public class User {

	private long id;
	private String firstName;
	private String lastName;
	private String username;
	private String phoneNumber;
	private String address;
	private String status;//make this enum
	private String email;
	private String education;//make this enum
	private String comments;
	private String specialization;
	private String profileLink;
	
	public User(){
		id=0;
	}
	
	public User(long id, String firstName, String lastName, String username, String phoneNumber, String address,
			String status, String email, String education, String comments, String specialization, String profileLink) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.status = status;
		this.email = email;
		this.education = education;
		this.comments = comments;
		this.specialization = specialization;
		this.profileLink = profileLink;
	}

	public User(long id, String username, String address, String email){
		this.id = id;
		this.username = username;
		this.address = address;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", address=" + address
				+ ", email=" + email + "]";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getProfileLink() {
		return profileLink;
	}

	public void setProfileLink(String profileLink) {
		this.profileLink = profileLink;
	}
	

	
}
