package com.fdu.interfaces;

import com.fdu.exception.ComputingServicesException;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.Role;

public interface RoleService {

	/**
	 * Update a given role and the privileges
	 * 
	 * @param role
	 *            role to update
	 * @return {@link ComputingServicesResponse} containing operation success
	 *         status
	 */
	default ComputingServicesResponse<Void> updateRole(Role role) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		try {
			update(role);
			response.setMessage("Updated role " + role.getRoleName() + " and privileges");
			response.setStatusCode(200);
		} catch (ComputingServicesException e) {
			response.setStatusCode(500);
			response.setMessage("Failed to update the role " + role.getRoleName() + " and its privileges");
		}
		return response;
	}

	public void update(Role role) throws ComputingServicesException;

}
