package com.fdu.interfaces;

import java.util.List;

import com.fdu.exception.ComputingServicesException;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.Role;

public interface RoleService {

	/**
	 * Save a new role and its privileges
	 * 
	 * @param role
	 *            role to save
	 * @return {@link ComputingServicesResponse} containing operation success
	 *         status
	 */
	default ComputingServicesResponse<Void> saveRole(Role role) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		try {
			save(role);
			response.setMessage("Saved the role - " + role.getRoleName() + " - and its privileges");
			response.setStatusCode(200);
		} catch (ComputingServicesException e) {
			response.setStatusCode(500);
			response.setMessage("Failed to save the role " + role.getRoleName() + " and its privileges");
		}
		return response;
	}

	/**
	 * Update a given role and the privileges
	 * 
	 * @param role
	 *            role to update
	 * @return {@link ComputingServicesResponse} containing operation success
	 *         status
	 */
	default ComputingServicesResponse<Void> updateRole(Role role, String originalRoleName) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		try {
			update(role, originalRoleName);
			response.setMessage("Updated the role - " + role.getRoleName() + " - and its privileges");
			response.setStatusCode(200);
		} catch (ComputingServicesException e) {
			response.setStatusCode(500);
			response.setMessage("Failed to update the role " + role.getRoleName() + " and its privileges");
		}
		return response;
	}

	/**
	 * Save a new role
	 * 
	 * @param role
	 *            role to save
	 * @throws ComputingServicesException
	 */
	void save(Role role) throws ComputingServicesException;

	/**
	 * Update a given role
	 * 
	 * @param role
	 *            role to update
	 * @param originalRoleName
	 *            original role name
	 * @throws ComputingServicesException
	 */
	void update(Role role, String originalRoleName) throws ComputingServicesException;

	/**
	 * get all the available roles and privileges associated with a role
	 * 
	 * @return {@link List} of {@link Role}s
	 */
	List<Role> getRoles();

	List<Role> getRoleNames();

	/**
	 * Delete a given role
	 * 
	 * @param roleId
	 *            role to delete
	 * @return boolean value indicating the operation success status
	 */
	boolean deleteRole(String roleId);
}
