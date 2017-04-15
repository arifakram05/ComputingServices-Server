package com.fdu.interfaces;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fdu.impl.ScheduleOperationsImpl;
import com.fdu.impl.ScheduleServiceImpl;
import com.fdu.model.ComputingServicesResponse;

public interface ScheduleOperations extends Connection {

	Map<String, Object> instanceMap = new ConcurrentHashMap<>();

	/**
	 * save all the given events in a lab schedule calendar
	 * 
	 * @param labschedule
	 *            schedule to save
	 * @return a {@link ComputingServicesResponse} containing success status of
	 *         the operation
	 */
	ComputingServicesResponse<Void> saveLabSchedule(String labschedule);

	/**
	 * Java 8 feature.<br/>
	 * Get an object of the implementations class
	 * 
	 * @return {@link ScheduleOperationsImpl} Object of the class that
	 *         implements this interface
	 */
	static ScheduleOperationsImpl getInstance() {
		if (instanceMap.get("ScheduleOperations") == null) {
			instanceMap.put("ScheduleOperations", new ScheduleOperationsImpl());
		}
		return (ScheduleOperationsImpl) instanceMap.get("ScheduleOperations");
	}

	default ScheduleService getScheduleServiceInstance() {
		if (instanceMap.get("ScheduleService") == null) {
			instanceMap.put("ScheduleService", new ScheduleServiceImpl(getDBConnection()));
		}
		return (ScheduleServiceImpl) instanceMap.get("ScheduleService");
	}
}
