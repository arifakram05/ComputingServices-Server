package com.fdu.interfaces;

import com.fdu.core.AdminOperationsImpl;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.LabSchedule;

/**
 * specifies all services for <b>admin</b> role
 * @author arifakrammohammed
 *
 */
public interface AdminOperations {
	
	ComputingServicesResponse saveLabSchedule(LabSchedule labSchedule);
	
	/**
	 * Java 8 feature.<br/>
	 * Get the object of the implementations class
	 * @return {@link AdminOperationsImpl} Object of the class that implements this interface
	 */
	static AdminOperationsImpl getInstance() {
		return new AdminOperationsImpl();
	}

}
