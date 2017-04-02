package com.fdu.impl;

import static com.mongodb.client.model.Filters.eq;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.fdu.constants.Constants;
import com.fdu.interfaces.ManagerService;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.LabAssistant;
import com.fdu.model.User;
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
		LOGGER.info("Deleted a job applicant with with ID " + studentId);
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
		LOGGER.info("Saved a new LA with ID " + labAssistant.getStudentId());
		return true;
	}

	@Override
	public ComputingServicesResponse<Void> authorizeUser(User user) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		// get collection
		MongoCollection<Document> userCollection = database.getCollection(Constants.USERS.getValue());
		Document document = new Document();
		document.append(Constants.USERID.getValue(), user.getUserId())
				.append(Constants.FIRSTNAME.getValue(), user.getFirstName())
				.append(Constants.LASTNAME.getValue(), user.getLastName())
				.append(Constants.ROLE.getValue(), user.getRole())
				.append(Constants.PASSWORD.getValue(), user.getPassword());
		// query
		userCollection.insertOne(document);
		LOGGER.info("Saved a new user with ID " + user.getUserId());
		response.setStatusCode(200);
		response.setMessage("User with ID " + user.getUserId() + " authorized to register");
		return response;
	}

}
