package com.fdu.interfaces;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fdu.impl.AssistantOperationsImpl;
import com.fdu.impl.AssistantServiceImpl;
import com.fdu.model.ComputingServicesResponse;

public interface AssistantOperations extends Connection {

	Map<String, Object> instanceMap = new ConcurrentHashMap<>();

	ComputingServicesResponse<Void> updateProfile(String labassistant, Object resume, Object photo);

	/**
	 * Java 8 feature.<br/>
	 * Get an object of the implementations class
	 * 
	 * @return {@link AssistantOperationsImpl} Object of the class that implements
	 *         this interface
	 */
	static AssistantOperationsImpl getInstance() {
		if(instanceMap.get("AssistantOperations") == null) {
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
