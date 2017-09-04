package com.fdu.impl;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.codehaus.jackson.map.ObjectMapper;

import com.fdu.constants.Constants;
import com.fdu.exception.ComputingServicesException;
import com.fdu.interfaces.ManagerService;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.LabAssistant;
import com.fdu.model.User;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.TextSearchOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;

public class ManagerServiceImpl implements ManagerService {

	private final static Logger LOGGER = Logger.getLogger(ManagerServiceImpl.class);

	MongoDatabase database;

	public ManagerServiceImpl(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public boolean deleteJobApplicant(String studentId) {
		// get collection
		MongoCollection<Document> jobApplicantsCollection = database.getCollection(Constants.JOBAPPLICANTS.getValue());
		// query
		DeleteResult result = jobApplicantsCollection.deleteOne(eq(Constants.STUDENTID.getValue(), studentId));
		LOGGER.info("Deleted a job applicant with with ID " + studentId);
		return result.wasAcknowledged();
	}

	@Override
	public boolean hireJobApplicant(LabAssistant labAssistant) throws ComputingServicesException {
		// get resume of the applicant
		byte[] resume = getFileDataFromDB(labAssistant.getStudentId());
		// set resume
		labAssistant.setResume(resume);
		// save lab assistant
		if (saveLabAssistant(labAssistant)) {
			// delete job applicant
			deleteJobApplicant(labAssistant.getStudentId());
			return true;
		}
		return false;
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
				.append(Constants.EDUCATION.getValue(), labAssistant.getEducation())
				.append(Constants.RESUME_EXTN.getValue(), labAssistant.getResumeExtn());
		// query
		labAssistantsCollection.insertOne(document);
		LOGGER.info("Saved a new LA with ID " + labAssistant.getStudentId());
		return true;
	}

	@Override
	public ComputingServicesResponse<Void> authorizeUser(User user) {
		ComputingServicesResponse<Void> response = new ComputingServicesResponse<>();
		if (isUserAuthorized(user)) {
			LOGGER.info("User with ID " + user.getUserId() + " is registered already");
			response.setStatusCode(404);
			response.setMessage("User with ID " + user.getUserId() + " is already authorized to register");
			return response;
		}
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

	@Override
	public boolean isUserAuthorized(User user) {
		// get collection
		MongoCollection<Document> userCollection = database.getCollection(Constants.USERS.getValue());
		if (userCollection.count(eq(Constants.USERID.getValue(), user.getUserId())) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<User> searchUsers(String searchText) {
		// get collection
		MongoCollection<Document> usersCollection = database.getCollection(Constants.LABASSISTANTS.getValue());
		List<User> userList = new ArrayList<>();
		// process each retrieved record
		Block<Document> processRetreivedData = (document) -> {

			String retrivedDataAsJSON = document.toJson();
			User user;
			LabAssistant labAssistant;
			try {
				labAssistant = new ObjectMapper().readValue(retrivedDataAsJSON, LabAssistant.class);
				user = new User();
				user.setFirstName(labAssistant.getFirstName());
				user.setLastName(labAssistant.getLastName());
				user.setUserId(labAssistant.getStudentId());
				userList.add(user);
			} catch (IOException e) {
				LOGGER.error("Error occurred while processing retrieved user details for search associates operation");
			}
		};
		/*
		 * in order to do a text search, you must have already created an index
		 * on associates collection, otherwise mongoDB throws error
		 */
		usersCollection.find(text(searchText, new TextSearchOptions().caseSensitive(false)))
				.forEach(processRetreivedData);
		return userList;
	}

	private byte[] getFileDataFromDB(String id) throws ComputingServicesException {
		// get collection
		MongoCollection<Document> laCollection = database.getCollection(Constants.JOBAPPLICANTS.getValue());
		// query
		Object[] result = laCollection.find(eq(Constants.STUDENTID.getValue(), id))
				.projection(Projections.include(Constants.RESUME.getValue())).first().values().toArray();
		// convert to byte array
		try {
			if (result[1] != null) {
				LOGGER.info("Binary data exists and obtained for " + id);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(result[1]);
				return baos.toByteArray();
			}
		} catch (Exception e) {
			LOGGER.error("Error while processing the retrieved file data");
			throw new ComputingServicesException("Error while doing file processing");
		}
		return null;
	}

	@Override
	public boolean updateJobApplicantStatus(String status, String studentId) {
		// get collection
		MongoCollection<Document> jobApplicantsCollection = database.getCollection(Constants.JOBAPPLICANTS.getValue());
		// query
		long updatedDocumentsCount = jobApplicantsCollection
				.updateOne(Filters.eq(Constants.STUDENTID.getValue(), studentId),
						Updates.set(Constants.STATUS.getValue(), status))
				.getModifiedCount();
		if (updatedDocumentsCount == 0) {
			return false;
		}
		return true;
	}

}
