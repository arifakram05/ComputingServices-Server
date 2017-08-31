package com.fdu.interfaces;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fdu.impl.AssistantOperationsImpl;
import com.fdu.impl.AssistantServiceImpl;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.StaffSchedule;

public interface AssistantOperations extends Connection {

	Map<String, Object> instanceMap = new ConcurrentHashMap<>();

	ComputingServicesResponse<Void> updateProfile(String labassistant, Object resume, Object photo);

	ComputingServicesResponse<StaffSchedule> schedule(String studentId, String date);

	ComputingServicesResponse<Void> recordTimesheet(String operation, String studentId, String datetime, String id);

	/**
	 * Java 8 feature.<br/>
	 * Get an object of the implementations class
	 * 
	 * @return {@link AssistantOperationsImpl} Object of the class that
	 *         implements this interface
	 */
	static AssistantOperations getInstance() {
		if (instanceMap.get("AssistantOperations") == null) {
			instanceMap.put("AssistantOperations", new AssistantOperationsImpl());
		}
		return (AssistantOperationsImpl) instanceMap.get("AssistantOperations");
	}

	default AssistantService getAssistantServiceInstance() {
		if (instanceMap.get("AssistantService") == null) {
			instanceMap.put("AssistantService", new AssistantServiceImpl(getDBConnection()));
		}
		return (AssistantServiceImpl) instanceMap.get("AssistantService");
	}

}
