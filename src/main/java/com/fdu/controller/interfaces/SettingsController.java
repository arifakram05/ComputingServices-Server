package com.fdu.controller.interfaces;

import java.util.Map;

import com.fdu.controller.impl.SettingsControllerImpl;
import com.fdu.model.ComputingServicesResponse;

public interface SettingsController {

	Map<String, String> getSettings();

	ComputingServicesResponse<Void> configureEmail(String email);
	
	ComputingServicesResponse<Void> changePassword(String passwordDetails) throws Exception;

	class Factory {
		private static final SettingsControllerImpl INSTANCE = new SettingsControllerImpl();

		public static SettingsControllerImpl getInstance() {
			return INSTANCE;
		}
	}
}
