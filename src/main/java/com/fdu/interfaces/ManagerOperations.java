package com.fdu.interfaces;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fdu.impl.CareersServiceImpl;
import com.fdu.impl.GeneralOperationsImpl;
import com.fdu.impl.ManagerOperationsImpl;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.JobApplicant;

public interface ManagerOperations extends Connection {

	Map<String, Object> instanceMap = new ConcurrentHashMap<>();

	/**
	 * View the details of all the students who applied for lab assistant job
	 * 
	 * @return a {@link ComputingServicesResponse} containing list of all job
	 *         applicants
	 */
	ComputingServicesResponse<JobApplicant> viewJobApplicants();

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

}
