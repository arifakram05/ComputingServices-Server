package com.fdu.impl;

import static com.mongodb.client.model.Filters.eq;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.fdu.constants.Constants;
import com.fdu.interfaces.ManagerService;
import com.fdu.model.LabAssistant;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

public class ManagerServiceImpl implements ManagerService {

	private final static Logger LOGGER = Logger.getLogger(ManagerServiceImpl.class);

	MongoDatabase database;

	public ManagerServiceImpl(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public boolean deleteJobApplicant(int studentId) {
		// get collection
		MongoCollection<Document> jobApplicantsCollection = database.getCollection(Constants.JOBAPPLICANTS.getValue());
		// query
		DeleteResult result = jobApplicantsCollection.deleteOne(eq(Constants.STUDENTID.getValue(), studentId));
		LOGGER.info("Deleted a job applicant with with ID "+studentId);
		return result.wasAcknowledged();
	}

	@Override
	public boolean hireJobApplicant(LabAssistant labAssistant) {
		// delete job applicant
		deleteJobApplicant(labAssistant.getStudentId());
		// save lab assistant
		return saveLabAssistant(labAssistant);
	}

	@Override
	public boolean saveLabAssistant(LabAssistant labAssistant) {
		// get collection
		MongoCollection<Document> labAssistantsCollection = database.getCollection(Constants.LABASSISTANTS.getValue());

		Document document = new Document();

		document.append(Constants.FIRSTNAME.getValue(), labAssistant.getFirstName())
				.append(Constants.LASTNAME.getValue(), labAssistant.getLastName())
				.append(Constants.DATEAPPPLIED.getValue(), labAssistant.getDateApplied())
				.append(Constants.DATEHIRED.getValue(), labAssistant.getDateHired())
				.append(Constants.EMAIL.getValue(), labAssistant.getEmail())
				.append(Constants.PHONE.getValue(), labAssistant.getPhone())
				.append(Constants.RESUME.getValue(), labAssistant.getResume())
				.append(Constants.STUDENTID.getValue(), labAssistant.getStudentId())
				.append(Constants.EDUCATION.getValue(), labAssistant.getEducation());
		// query
		labAssistantsCollection.insertOne(document);
		LOGGER.info("Saved a new LA with ID "+labAssistant.getStudentId());
		return true;
	}

}
