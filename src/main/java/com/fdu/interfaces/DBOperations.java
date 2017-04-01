package com.fdu.interfaces;

import com.fdu.dbimpl.ComputingServicesDBOperations;
import com.fdu.model.LabSchedule;

public interface DBOperations {
	
	boolean saveLabSchedule(LabSchedule labSchedule);

	/**
	 * Java 8 feature.<br/>
	 * Get the object of the implementations class
	 * @return {@link ComputingServicesDBOperations} Object of the class that implements this interface
	 */
	static ComputingServicesDBOperations getInstance() {
		return new ComputingServicesDBOperations();
	}
}
