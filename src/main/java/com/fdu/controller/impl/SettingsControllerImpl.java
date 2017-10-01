package com.fdu.controller.impl;

import java.util.Map;

import org.codehaus.jettison.json.JSONObject;

import com.fdu.controller.interfaces.SettingsController;
import com.fdu.database.dao.SettingsDAO;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.util.GenericUtility;

public class SettingsControllerImpl implements SettingsController {

	@Override
	public Map<String, String> getSettings() {
		return SettingsDAO.Factory.getInstance().getSettings();
	}

	@Override
	public ComputingServicesResponse<Void> configureEmail(String email) {
		String message = null;
		if (SettingsDAO.Factory.getInstance().updateEmail(email)) {
			message = "Updated email address";
			return GenericUtility.createSuccessResponse(message);
		} else {
			message = "Failed to update email address";
			return GenericUtility.createFailureResponse(message);
		}
	}

	@Override
	public ComputingServicesResponse<Void> changePassword(String passwordDetails) throws Exception {
		String message = null;
		JSONObject jsonObject = new JSONObject(passwordDetails);
		String oldPassword = jsonObject.getString("oldPassword");
		String newPassword = jsonObject.getString("newPassword");
		String userId = jsonObject.getString("userId");
		if (SettingsDAO.Factory.getInstance().changePassword(oldPassword, newPassword, userId)) {
			message = "Updated password";
			return GenericUtility.createSuccessResponse(message);
		} else {
			message = "Failed to update email address";
			throw new Exception(message);
		}
	}

}
