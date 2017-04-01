package com.fdu.core;

import org.codehaus.jackson.map.ObjectMapper;

import com.fdu.interfaces.AdminOperations;
import com.fdu.interfaces.DBOperations;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.JobApplicant;
import com.fdu.model.LabAssistant;
import com.fdu.model.LabSchedule;
import com.fdu.util.GenericUtility;

public class AdminOperationsImpl implements AdminOperations {

	@Override
	public ComputingServicesResponse saveLabSchedule(LabSchedule labSchedule) {
		boolean isSuccess = DBOperations.getInstance().saveLabSchedule(labSchedule);
		if(isSuccess) {
			return GenericUtility.response("Lab Schedule saved!", 1);			
		} else {
			return GenericUtility.response("Something went wrong! Details could not be saved.", 3);
		}
	}
}
