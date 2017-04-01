package com.fdu.interfaces;

import com.fdu.impl.ComputingServicesOperations;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.LabSchedule;

/**
 * Defines every service this product has to offer
 * @author arifakrammohammed
 *
 */
public interface Operations {

	/**
	 * save lab schedule
	 * @param labSchedule the details about a session occurring in a lab
	 * @return {@link ComputingServicesResponse} with success or failure details
	 */
	ComputingServicesResponse saveLabSchedule(LabSchedule labSchedule);

	/**
	 * Java 8 feature.<br/>
	 * Get the object of the implementations class
	 * @return {@link ComputingServicesOperations} Object of the class that implements this interface
	 */
	static ComputingServicesOperations getInstance() {
		return new ComputingServicesOperations();
	}
}
