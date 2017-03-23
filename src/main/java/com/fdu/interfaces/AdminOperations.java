package com.fdu.interfaces;

import com.fdu.core.AdminOperationsImpl;
import com.fdu.model.JobApplicant;
import com.fdu.model.LabSchedule;
import com.fdu.response.ComputingServicesResponse;

/**
 * specifies all services for <b>admin</b> role
 * @author arifakrammohammed
 *
 */
public interface AdminOperations {
	
	/**
	 * hire a job applicant for a role of Lab Assistant
	 * @param jobApplicant the details of the job applicant
	 * @return <b>boolean</b> true if operation success, false otherwise
	 */
	ComputingServicesResponse hireJobApplicant(JobApplicant jobApplicant);
	
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
