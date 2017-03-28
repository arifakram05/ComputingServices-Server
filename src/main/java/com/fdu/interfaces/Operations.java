package com.fdu.interfaces;

import java.util.List;

import com.fdu.impl.ComputingServicesOperations;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.JobApplicant;
import com.fdu.model.LabSchedule;

/**
 * Defines every service this product has to offer
 * @author arifakrammohammed
 *
 */
public interface Operations {

	/**
	 * saves the details of a job applicant for Lab Assistant position to the database
	 * @param jobApplicantDetails Details of the person applying for a Lab Assistant position
	 * @return boolean value indication operation success status; true if
	 *         success, false otherwise
	 */
	boolean saveJobApplicant(JobApplicant jobApplicantDetails);
	
	/**
	 * view all job applicants
	 * @return {@link List} of all {@link JobApplicant}s
	 */
	List<JobApplicant> getAllJobApplicants();
	
	/**
	 * hire a job applicant for a role of Lab Assistant
	 * @param jobApplicant the details of the job applicant
	 * @return <b>boolean</b> true if operation success, false otherwise
	 */
	ComputingServicesResponse hireJobApplicant(JobApplicant jobApplicant);
	
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
