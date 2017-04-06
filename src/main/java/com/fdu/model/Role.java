package com.fdu.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Role {

	private Object _id;
	private String roleName;
	private List<Privilege> availablePrivs;
	private List<Privilege> assignedPrivs;

	public Object get_id() {
		return _id;
	}

	public void set_id(Object _id) {
		this._id = _id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Privilege> getAvailablePrivs() {
		return availablePrivs;
	}

	public void setAvailablePrivs(List<Privilege> availablePrivs) {
		this.availablePrivs = availablePrivs;
	}

	public List<Privilege> getAssignedPrivs() {
		return assignedPrivs;
	}

	public void setAssignedPrivs(List<Privilege> assignedPrivs) {
		this.assignedPrivs = assignedPrivs;
	}
}
