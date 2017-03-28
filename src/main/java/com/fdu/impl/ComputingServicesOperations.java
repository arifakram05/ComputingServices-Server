package com.fdu.impl;

import java.util.List;

import com.fdu.interfaces.AdminOperations;
import com.fdu.interfaces.DBOperations;
import com.fdu.interfaces.Operations;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.JobApplicant;
import com.fdu.model.LabSchedule;

public class ComputingServicesOperations implements Operations {

	@Override
	public boolean saveJobApplicant(JobApplicant jobApplicantDetails) {
		return DBOperations.getInstance().saveJobApplicant(jobApplicantDetails) ? true : false;
	}

	@Override
	public List<JobApplicant> getAllJobApplicants() {
		return DBOperations.getInstance().getAllJobApplicants();
	}

	@Override
	public ComputingServicesResponse hireJobApplicant(JobApplicant jobApplicant) {
		return AdminOperations.getInstance().hireJobApplicant(jobApplicant);
	}

	@Override
	public ComputingServicesResponse saveLabSchedule(LabSchedule labSchedule) {
		return AdminOperations.getInstance().saveLabSchedule(labSchedule);
	}

}
