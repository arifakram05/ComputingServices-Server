package com.fdu.interfaces;

import java.util.List;

import com.fdu.dbimpl.ComputingServicesDBOperations;
import com.fdu.model.JobApplicant;
import com.fdu.model.LabAssistant;
import com.fdu.model.LabSchedule;

public interface DBOperations {

	/**
	 * view all job applicants
	 * @return {@link List} of all {@link JobApplicant}s
	 */
	List<JobApplicant> getAllJobApplicants();
	
	/**
	 * saves the details of lab assistant to the database
	 * @param jobApplicant the details of the job applicant who is being hired as a Lab Assistant
	 * @return <b>boolean</b> true if operation success, false otherwise
	 */
	boolean saveLabAssistant(LabAssistant labAssistant);
	
	/**
	 * deletes the job applicant details from the database
	 * @param studentId the id of the job applicant who
	 * @return <b>boolean</b> true if operation success, false otherwise
	 */
	boolean deleteJobApplicant(Integer studentId);
	
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
