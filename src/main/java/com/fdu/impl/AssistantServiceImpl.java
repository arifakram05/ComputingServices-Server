package com.fdu.impl;

import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Sorts.ascending;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.codehaus.jackson.map.ObjectMapper;

import com.fdu.constants.Constants;
import com.fdu.interfaces.AssistantService;
import com.fdu.model.LabAssistant;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class AssistantServiceImpl implements AssistantService {

	private final static Logger LOGGER = Logger.getLogger(AssistantServiceImpl.class);

	MongoDatabase database;

	public AssistantServiceImpl(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public List<LabAssistant> viewAllLabAssistants() {
		List<LabAssistant> allLabAssistants = new ArrayList<>();
		// get collection
		MongoCollection<Document> labAssistantCollection = database.getCollection(Constants.LABASSISTANTS.getValue());
		// processed retrieved data
		Block<Document> processRetreivedData = (document) -> {

			String retrivedDataAsJSON = document.toJson();
			LabAssistant labAssistant;
			try {
				labAssistant = new ObjectMapper().readValue(retrivedDataAsJSON, LabAssistant.class);
				allLabAssistants.add(labAssistant);
			} catch (IOException e) {
				LOGGER.error("Error while processing retrieved lab assistant details ", e);
			}
		};
		// query
		labAssistantCollection.find().projection(fields(excludeId())).sort(ascending(Constants.LASTNAME.getValue()))
				.forEach(processRetreivedData);
		LOGGER.info("All job applicant details fetched");
		return allLabAssistants;
	}

}
