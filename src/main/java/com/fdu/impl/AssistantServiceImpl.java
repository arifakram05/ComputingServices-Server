package com.fdu.impl;

import static com.mongodb.client.model.Filters.eq;
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
import com.fdu.exception.ComputingServicesException;
import com.fdu.interfaces.AssistantService;
import com.fdu.model.LabAssistant;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

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

	@Override
	public boolean deleteLabAssistant(int studentId) {
		// get collection
		MongoCollection<Document> labAssistantCollection = database.getCollection(Constants.LABASSISTANTS.getValue());
		// query
		DeleteResult result = labAssistantCollection.deleteOne(eq(Constants.STUDENTID.getValue(), studentId));
		LOGGER.info("Deleted a Lab Assistant with ID "+studentId);
		return result.wasAcknowledged();
	}

	@Override
	public void updateLA(LabAssistant labAssistant) throws ComputingServicesException {
		try {
			// get collection
			MongoCollection<Document> labAssistantCollection = database.getCollection(Constants.LABASSISTANTS.getValue());
			// create document to save
			Document laDetailsDocument = new Document();
			// add stuff to update
			laDetailsDocument.put(Constants.STATUS.getValue(), labAssistant.getStatus().getValue());
			laDetailsDocument.put(Constants.COMMENTS.getValue(), labAssistant.getComments());
	
			Document command = new Document();
			command.put("$set", laDetailsDocument);
			// update lab assistant details
			labAssistantCollection.updateOne(eq(Constants.STUDENTID.getValue(), labAssistant.getStudentId()), command);
			LOGGER.info("Updated Lab Assistant with ID "+labAssistant.getStudentId());
		} catch (Exception e) {
			LOGGER.error("Error occurred while updating lab assistant "+labAssistant.getStudentId(), e);
			throw new ComputingServicesException(e.getMessage());
		}
	}

}
