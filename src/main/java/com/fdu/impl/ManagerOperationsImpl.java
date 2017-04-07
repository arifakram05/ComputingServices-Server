package com.fdu.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.fdu.interfaces.ManagerOperations;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.JobApplicant;
import com.fdu.model.LabAssistant;
import com.fdu.model.Role;
import com.fdu.model.User;

public class ManagerOperationsImpl implements ManagerOperations {

	private final static Logger LOGGER = Logger.getLogger(ManagerOperationsImpl.class);

	@Override
	public ComputingServicesResponse<JobApplicant> viewJobApplicants() {
		ComputingServicesResponse<JobApplicant> response = new ComputingServicesResponse<>();
		List<JobApplicant> jobApplicantList = null;
		try {
			LOGGER.info("Preparing to fetch all job applicants");
			jobApplicantList = getCareersServiceInstance().viewAllJobApplicants();
			LOGGER.info("All job applicants successfully retrieved");
			response.setStatusCode(200);
			response.setMessage("List of all Job Applicants");
			response.setResponse(jobApplicantList);
		} catch (Exception e) {
			LOGGER.error("Error while fetching all job applicants ", e);
			response.setStatusCode(500);
			response.setMessage("Error occurred. Could not get job applicant list");
		}
		return response;
	}

	@Override
	public ComputingServicesResponse<Void> deleteJobApplicant(int studentId) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		try {
			LOGGER.info("Preparing to delete job applicant");
			if(getManagerServiceInstance().deleteJobApplicant(studentId)) {
				LOGGER.info("Job applicant deleted - "+studentId);
				response.setStatusCode(200);
				response.setMessage("Successfully deleted job applicant with ID "+studentId);
			} else {
				LOGGER.info("Job applicant could not NOT deleted - "+studentId);
				response.setStatusCode(404);
				response.setMessage("Could not delete job applicant with ID "+studentId);
			}
		} catch (Exception e) {
			LOGGER.error("Error while deleting job applicant "+studentId, e);
			response.setStatusCode(500);
			response.setMessage("Error occurred. Could not delete job applicant");
		}
		return response;
	}

	@Override
	public ComputingServicesResponse<Void> hireJobApplicant(String labAssistantDetails) {
		ComputingServicesResponse<Void> response = null;
		LabAssistant labAssistant = null;
		try {
			LOGGER.info("Preparing to hire job applicant");
			labAssistant = new ObjectMapper().readValue(labAssistantDetails, LabAssistant.class);
			LOGGER.info("Hiring job applicant " + labAssistant.getStudentId());
			response = getManagerServiceInstance().hire(labAssistant);
			LOGGER.info("Hire job applicant success " + labAssistant.getStudentId());
		} catch (Exception e) {
			LOGGER.error("Error while hiring job applicant " + labAssistant.getStudentId(), e);
			response = new ComputingServicesResponse<>();
			response.setStatusCode(500);
			response.setMessage("Error Occurred while processing hire operation for "+labAssistant.getStudentId());
		}
		return response;
	}

	@Override
	public ComputingServicesResponse<LabAssistant> viewLabAssistants() {
		ComputingServicesResponse<LabAssistant> response = new ComputingServicesResponse<>();
		List<LabAssistant> labAssistantList = null;
		try {
			LOGGER.info("Preparing to fetch all lab assistants");
			labAssistantList = getAssistantServiceInstance().viewAllLabAssistants();
			LOGGER.info("All lab assistants successfully retrieved");
			response.setStatusCode(200);
			response.setMessage("List of all lab assistants");
			response.setResponse(labAssistantList);
		} catch (Exception e) {
			LOGGER.error("Error while fetching all lab assistants ", e);
			response.setStatusCode(500);
			response.setMessage("Error occurred. Could not get lab assistant list");
		}
		return response;
	}

	@Override
	public ComputingServicesResponse<Void> deleteLabAssistant(int studentId) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		try {
			LOGGER.info("Preparing to delete lab assistant");
			if(getAssistantServiceInstance().deleteLabAssistant(studentId)) {
				LOGGER.info("Lab Assistant deleted - "+studentId);
				response.setStatusCode(200);
				response.setMessage("Successfully deleted lab assistant with ID "+studentId);
			} else {
				LOGGER.info("Lab Assistant could NOT be deleted - "+studentId);
				response.setStatusCode(404);
				response.setMessage("Could not delete lab assistant with ID "+studentId);
			}
		} catch (Exception e) {
			LOGGER.error("Error while deleting lab assistant "+studentId, e);
			response.setStatusCode(500);
			response.setMessage("Error occurred. Could not delete lab assistant");
		}
		return response;
	}

	@Override
	public ComputingServicesResponse<Void> updateLabAssistant(String labAssistantDetails) {
		ComputingServicesResponse<Void> response = null;
		LabAssistant labAssistant = null;
		try {
			LOGGER.info("Preparing to update lab assistant");
			labAssistant = new ObjectMapper().readValue(labAssistantDetails, LabAssistant.class);
			LOGGER.info("Updating Lab Assistant " + labAssistant.getStudentId());
			response = getAssistantServiceInstance().updateLabAssistant(labAssistant);
			LOGGER.info("Update Lab Assistant success " + labAssistant.getStudentId());
		} catch (Exception e) {
			LOGGER.error("Error while updating lab assistant details " + labAssistant.getStudentId(), e);
			response = new ComputingServicesResponse<>();
			response.setStatusCode(500);
			response.setMessage("Error occurred while updating lab assistant with ID "+labAssistant.getStudentId());
		}
		return response;
	}

	@Override
	public ComputingServicesResponse<Void> authorizeUser(String userDetails) {
		ComputingServicesResponse<Void> response = null;
		User user = null;
		try {
			LOGGER.info("Preparing to grant a user access to the system");
			user = new ObjectMapper().readValue(userDetails, User.class);
			LOGGER.info("Authorizing for registering " + user.getUserId());
			response = getManagerServiceInstance().authorizeUser(user);
			LOGGER.info("Granting user system access success " + user.getUserId());
		} catch (Exception e) {
			LOGGER.error("Error while authorizing user " + user.getUserId(), e);
			response = new ComputingServicesResponse<>();
			response.setStatusCode(500);
			response.setMessage("Error occurred while authorizing user with ID "+user.getUserId());
		}
		return response;
	}

	@Override
	public ComputingServicesResponse<Void> updateRole(String roleDetails) {
		ComputingServicesResponse<Void> response = null;
		Role role = null;
		try {
			LOGGER.info("Preparing to update role and privileges");
			role = new ObjectMapper().readValue(roleDetails, Role.class);
			LOGGER.info("Updating role " + role.getRoleName());
			response = getRoleServiceInstance().updateRole(role);
			LOGGER.info("Updating role and privs success " + role.getRoleName());
		} catch (Exception e) {
			LOGGER.error("Error while updating role and priv details " + role.getRoleName(), e);
			response = new ComputingServicesResponse<>();
			response.setStatusCode(500);
			response.setMessage("Error occurred while updating role "+role.getRoleName()+" and its privileges");
		}
		return response;
	}

	@Override
	public ComputingServicesResponse<Role> getRoles() {
		ComputingServicesResponse<Role> response = new ComputingServicesResponse<>();
		List<Role> rolesList = null;
		try {
			LOGGER.info("Preparing to fetch all roles and privileges");
			rolesList = getRoleServiceInstance().getRoles();
			LOGGER.info("All roles successfully retrieved");
			response.setStatusCode(200);
			response.setMessage("List of all roles");
			response.setResponse(rolesList);
		} catch (Exception e) {
			LOGGER.error("Error while fetching all roles ", e);
			response.setStatusCode(500);
			response.setMessage("Error occurred. Could not get roles and privileges list");
		}
		return response;
	}

	@Override
	public ComputingServicesResponse<Void> saveRole(String roleDetails) {
		ComputingServicesResponse<Void> response = null;
		Role role = null;
		try {
			LOGGER.info("Preparing to save role and privileges");
			role = new ObjectMapper().readValue(roleDetails, Role.class);
			LOGGER.info("Saving role " + role.getRoleName());
			response = getRoleServiceInstance().saveRole(role);
			LOGGER.info("Saving role and privs success " + role.getRoleName());
		} catch (Exception e) {
			LOGGER.error("Error while saving role and priv details " + role.getRoleName(), e);
			response = new ComputingServicesResponse<>();
			response.setStatusCode(500);
			response.setMessage("Error occurred while saving role "+role.getRoleName()+" and its privileges");
		}
		return response;
	}

}
