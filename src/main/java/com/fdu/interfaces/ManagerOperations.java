package com.fdu.interfaces;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fdu.impl.AssistantServiceImpl;
import com.fdu.impl.CareersServiceImpl;
import com.fdu.impl.GeneralOperationsImpl;
import com.fdu.impl.ManagerOperationsImpl;
import com.fdu.impl.ManagerServiceImpl;
import com.fdu.impl.RoleServiceImpl;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.JobApplicant;
import com.fdu.model.LabAssistant;
import com.fdu.model.Role;

public interface ManagerOperations extends Connection {

	Map<String, Object> instanceMap = new ConcurrentHashMap<>();

	/*
	 * Below are services related to Job Applicant
	 */

	/**
	 * View the details of all the students who applied for lab assistant job
	 * 
	 * @return a {@link ComputingServicesResponse} containing list of all job
	 *         applicants
	 */
	ComputingServicesResponse<JobApplicant> viewJobApplicants();

	ComputingServicesResponse<Void> deleteJobApplicant(String studentId);

	ComputingServicesResponse<Void> hireJobApplicant(String labAssistant);

	/*
	 * Below are services related to Lab Assistant
	 */

	/**
	 * Get all the lab assistant details
	 * 
	 * @return a {@link ComputingServicesResponse} containing list of all lab
	 *         assistants
	 */
	ComputingServicesResponse<LabAssistant> viewLabAssistants();

	/**
	 * Delete a Lab Assistant. This operation removes a Lab Assistant from the
	 * DB records
	 * 
	 * @param studentId
	 *            ID of the Lab Assistant to be deleted
	 * @return a {@link ComputingServicesResponse} containing success status of
	 *         the operation
	 */
	ComputingServicesResponse<Void> deleteLabAssistant(String studentId);

	/**
	 * Update the details of a given lab assistant
	 * 
	 * @param labAssistant
	 *            LA whose details are to be updated
	 * @return a {@link ComputingServicesResponse} containing success status of
	 *         the operation
	 */
	ComputingServicesResponse<Void> updateLabAssistant(String labAssistant);

	/**
	 * Grant a user access for registering with this system
	 * 
	 * @param user
	 *            user to register
	 * @return a {@link ComputingServicesResponse} containing success status of
	 *         the operation
	 */
	ComputingServicesResponse<Void> authorizeUser(String user);

	/*
	 * Below are related to roles and privileges
	 */

	/**
	 * Save a new role and its privileges
	 * 
	 * @param role
	 *            role and privileges to save
	 * @return a {@link ComputingServicesResponse} containing success status of
	 *         the operation
	 */
	ComputingServicesResponse<Void> saveRole(String role);

	/**
	 * Update a role and privileges
	 * 
	 * @param role
	 *            role and privileges to update
	 * @return a {@link ComputingServicesResponse} containing success status of
	 *         the operation
	 */
	ComputingServicesResponse<Void> updateRole(String role);

	/**
	 * Get all roles and privileges associated with the role
	 * 
	 * @return a {@link ComputingServicesResponse} containing list of all roles
	 */
	ComputingServicesResponse<Role> getRoles();

	ComputingServicesResponse<Role> getRoleNames();

	/**
	 * Delete a role
	 * 
	 * @param roleId
	 *            Role Id to delete
	 * @return a {@link ComputingServicesResponse} containing success status of
	 *         the operation
	 */
	ComputingServicesResponse<Void> deleteRole(String roleId);

	Object download(int studentId);

	/**
	 * Java 8 feature.<br/>
	 * Get an object of the implementations class
	 * 
	 * @return {@link GeneralOperationsImpl} Object of the class that implements
	 *         this interface
	 */
	static ManagerOperationsImpl getInstance() {
		if (instanceMap.get("ManagerOperations") == null) {
			instanceMap.put("ManagerOperations", new ManagerOperationsImpl());
		}
		return (ManagerOperationsImpl) instanceMap.get("ManagerOperations");
	}

	/*
	 * below methods provide instances of implementation classes
	 */

	default CareersService getCareersServiceInstance() {
		if (instanceMap.get("CareersService") == null) {
			instanceMap.put("CareersService", new CareersServiceImpl(getDBConnection()));
		}
		return (CareersServiceImpl) instanceMap.get("CareersService");
	}

	default AssistantService getAssistantServiceInstance() {
		if (instanceMap.get("AssistantService") == null) {
			instanceMap.put("AssistantService", new AssistantServiceImpl(getDBConnection()));
		}
		return (AssistantServiceImpl) instanceMap.get("AssistantService");
	}

	default ManagerService getManagerServiceInstance() {
		if (instanceMap.get("ManagerService") == null) {
			instanceMap.put("ManagerService", new ManagerServiceImpl(getDBConnection()));
		}
		return (ManagerServiceImpl) instanceMap.get("ManagerService");
	}

	default RoleService getRoleServiceInstance() {
		if (instanceMap.get("RoleService") == null) {
			instanceMap.put("RoleService", new RoleServiceImpl(getDBConnection()));
		}
		return (RoleServiceImpl) instanceMap.get("RoleService");
	}

}
