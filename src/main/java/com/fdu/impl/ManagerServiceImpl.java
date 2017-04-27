package com.fdu.impl;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.codehaus.jackson.map.ObjectMapper;

import com.fdu.constants.Constants;
import com.fdu.interfaces.ManagerService;
import com.fdu.model.ComputingServicesResponse;
import com.fdu.model.LabAssistant;
import com.fdu.model.User;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.TextSearchOptions;
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
	public boolean hireJobApplicant(LabAssistant labAssistant) {
		// delete job applicant
		deleteJobApplicant(labAssistant.getStudentId());
		// save lab assistant
		if (saveLabAssistant(labAssistant)) {
			// save resume
			// TODO: Get resume from jobapplicants and save it in labassistants
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
			LOGGER.info("User with ID " + user.getUserId()+" is registered already");
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
		/*in order to do a text search, you must have already created an index on associates collection, otherwise mongoDB throws error*/
		usersCollection.find(text(searchText, new TextSearchOptions().caseSensitive(false))).forEach(processRetreivedData);
		return userList;
	}

}
