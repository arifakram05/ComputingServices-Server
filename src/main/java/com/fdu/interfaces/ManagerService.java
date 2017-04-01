package com.fdu.interfaces;

import org.apache.log4j.Logger;

public interface ManagerService {

	final static Logger LOGGER = Logger.getLogger(ManagerService.class);

	boolean deleteJobApplicant(int studentId);

}
