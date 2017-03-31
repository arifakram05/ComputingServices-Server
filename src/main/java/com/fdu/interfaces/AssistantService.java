package com.fdu.interfaces;

import java.util.List;

import org.apache.log4j.Logger;

import com.fdu.model.LabAssistant;

public interface AssistantService {

	final static Logger LOGGER = Logger.getLogger(AssistantService.class);

	/**
	 * Get the list of all lab assistants
	 * 
	 * @return {@link List} of {@link LabAssistant}s
	 */
	List<LabAssistant> viewAllLabAssistants();

}
